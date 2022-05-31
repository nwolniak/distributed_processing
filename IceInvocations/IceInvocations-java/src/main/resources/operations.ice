
#ifndef CALC_ICE
#define CALC_ICE

module Operations
{
  exception NoInput {};

  sequence<double> doubleSeq;
  sequence<byte> imageData;

  struct Statistics
  {
    double avg;
    double median;
    double min;
    double max;
  };

  struct Author
  {
    string firstName;
    string secondName;
  };

  struct Image
  {
    string imageType;
    imageData imageData;
  };

  struct Book
  {
    string bookName;
    Author author;
    long length;
    Image bookImage;
  };

  sequence<Book> bookSeq;

  dictionary <Author, bookSeq> booksGroupedByAuthor;

  interface Operator
  {
    idempotent Statistics calculateStatistics(doubleSeq data);
    idempotent booksGroupedByAuthor groupByAuthor(bookSeq books);
    idempotent Image convertToGrayScale(Image image);
  };

};


#endif
