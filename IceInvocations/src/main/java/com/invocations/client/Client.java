package com.invocations.client;


import Operations.*;
import com.zeroc.Ice.*;
import com.zeroc.Ice.InputStream;
import com.zeroc.Ice.Object.Ice_invokeResult;
import com.zeroc.Ice.OutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Client {
    private static final java.util.logging.Logger logger = Logger.getLogger(Client.class.getName());
    private final Communicator communicator;
    private final ObjectPrx proxy;
    private int counter;

    public Client(String[] args, String path) {
        this.communicator = Util.initialize(args);
        this.proxy = this.communicator.propertyToProxy(path);
        if (this.proxy == null) {
            logger.info("Proxy is null");
            this.shutdown();
        }
        this.counter = 0;
    }


    public static void main(String[] args) {
        Client client = new Client(args, "Client1.Proxy");
        client.run();
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            OutputStream outputStream;
            String imageType;
            Image image;
            byte[] imageData;
            do {
                System.out.print("command>");
                switch (br.readLine()) {
                    case "1":
                        double[] data = IntStream.range(0, 1000).asDoubleStream().toArray();
                        outputStream = new OutputStream(communicator);
                        outputStream.startEncapsulation();
                        outputStream.writeDoubleSeq(data);
                        outputStream.endEncapsulation();
                        byte[] data1000 = outputStream.finished();
                        this.calculateStatistics(data1000);
                        break;
                    case "2":
                        Book[] books = this.createBooks();
                        outputStream = new OutputStream(communicator);
                        outputStream.startEncapsulation();
                        bookSeqHelper.write(outputStream, books);
                        outputStream.endEncapsulation();
                        byte[] books10 = outputStream.finished();
                        this.groupByAuthor(books10);
                        break;
                    case "3":
                        imageData = Files.readAllBytes(Paths.get("img/kitten.jpg"));
                        imageType = ".jpg";
                        image = new Image(imageType, imageData);
                        outputStream = new OutputStream(communicator);
                        outputStream.startEncapsulation();
                        Image.ice_write(outputStream, image);
                        outputStream.endEncapsulation();
                        byte[] image1 = outputStream.finished();
                        this.convertToGrayScale(image1);
                        break;
                    case "4":
                        imageData = Files.readAllBytes(Paths.get("img/dog.png"));
                        imageType = ".png";
                        image = new Image(imageType, imageData);
                        outputStream = new OutputStream(communicator);
                        outputStream.startEncapsulation();
                        Image.ice_write(outputStream, image);
                        outputStream.endEncapsulation();
                        byte[] image2 = outputStream.finished();
                        this.convertToGrayScale(image2);
                        break;
                    case "q":
                        this.shutdown();
                        break;
                    default:
                        break;
                }
            } while (true);

        } catch (IOException e) {
            logger.info("IOException " + e.getMessage());
        } catch (ConnectionRefusedException e) {
            logger.info("Connection refused " + e.getMessage());
            this.shutdown();
        }
    }

    public void calculateStatistics(byte[] bytes) {
        Ice_invokeResult res = this.proxy.ice_invoke("calculateStatistics", OperationMode.Idempotent, bytes);
        if (res.returnValue) {
            InputStream inputStream = new InputStream(communicator, res.outParams);
            inputStream.startEncapsulation();
            Statistics statistics = new Statistics();
            statistics.ice_readMembers(inputStream);
            inputStream.endEncapsulation();
            System.out.println("Min:" + statistics.min
                    + " Max:" + statistics.max
                    + " Avg:" + statistics.avg
                    + " Median:" + statistics.median);
        }
    }

    public void groupByAuthor(byte[] bytes) {
        Ice_invokeResult res = this.proxy.ice_invoke("groupByAuthor", OperationMode.Idempotent, bytes);
        if (res.returnValue) {
            InputStream inputStream = new InputStream(communicator, res.outParams);
            inputStream.startEncapsulation();
            Map<Author, Book[]> booksGroupedByAuthor = booksGroupedByAuthorHelper.read(inputStream);
            inputStream.endEncapsulation();
            booksGroupedByAuthor.forEach((key, value) -> System.out.println(key.firstName + " " + key.secondName +
                    " : " + Arrays.stream(value).map(book -> "(" + book.bookName + " " +
                    book.author.firstName + " " + book.author.secondName + " " + book.length + " " + book.bookImage + ")")
                    .collect(Collectors.toList())));

        }
    }
    
    private void convertToGrayScale(byte[] bytes) {
        Ice_invokeResult res = this.proxy.ice_invoke("convertToGrayScale", OperationMode.Idempotent, bytes);
        if (res.returnValue) {
            InputStream inputStream = new InputStream(communicator, res.outParams);
            inputStream.startEncapsulation();
            Image image = Image.ice_read(inputStream);
            inputStream.endEncapsulation();

            File file = new File(String.format("./img_grayscale/grayscale%s%s", this.counter, image.imageType));
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, false);){
                fileOutputStream.write(image.imageData);
                this.counter++;
                System.out.println("Image saved");
            } catch (IOException e) {
                logger.info("Image saving error " + e.getMessage());
            }
        }
    }

    private Book[] createBooks() throws IOException {
        Book[] books = new Book[10];
        books[0] = new Book("book1", new Author("FirstName1", "SecondName1"), 100, new Image(".jpg", Files.readAllBytes(Paths.get("img/kitten.jpg"))));
        books[1] = new Book("book2", new Author("FirstName1", "SecondName1"), 300, new Image(".jpg", Files.readAllBytes(Paths.get("img/kitten.jpg"))));
        books[2] = new Book("book3", new Author("FirstName1", "SecondName1"), 250, new Image(".jpg", Files.readAllBytes(Paths.get("img/kitten.jpg"))));
        books[3] = new Book("book4", new Author("FirstName2", "SecondName2"), 320, new Image(".jpg", Files.readAllBytes(Paths.get("img/kitten.jpg"))));
        books[4] = new Book("book5", new Author("FirstName2", "SecondName2"), 50, new Image(".jpg", Files.readAllBytes(Paths.get("img/kitten.jpg"))));
        books[5] = new Book("book6", new Author("FirstName2", "SecondName2"), 840, new Image(".png", Files.readAllBytes(Paths.get("img/dog.png"))));
        books[6] = new Book("book7", new Author("FirstName3", "SecondName3"), 350, new Image(".png", Files.readAllBytes(Paths.get("img/dog.png"))));
        books[7] = new Book("book8", new Author("FirstName4", "SecondName4"), 150, new Image(".png", Files.readAllBytes(Paths.get("img/dog.png"))));
        books[8] = new Book("book9", new Author("FirstName5", "SecondName5"), 180, new Image(".png", Files.readAllBytes(Paths.get("img/dog.png"))));
        books[9] = new Book("book10", new Author("FirstName5", "SecondName5"), 280, new Image(".png", Files.readAllBytes(Paths.get("img/dog.png"))));
        return books;
    }

    public void shutdown() {
        this.communicator.destroy();
        System.exit(1);
    }

}
