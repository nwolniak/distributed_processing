import sys
import Ice

import Operations
from Operations import Statistics
from Operations import Image

from collections import defaultdict
from statistics import mean
from statistics import median
import cv2
import numpy as np


class OperationsI(Operations.Operator):
    def calculateStatistics(self, data, current=None):
        print("calculateStatistics")
        statistics = Statistics(
            min=min(data),
            max=max(data),
            avg=mean(data),
            median=median(data)
        )
        return statistics

    def groupByAuthor(self, books, current=None):
        print("groupByAuthor")
        booksGroupedByAuthor = defaultdict(list)
        for book in books:
            booksGroupedByAuthor[book.author].append(book)
        return booksGroupedByAuthor

    def convertToGrayScale(self, image, current=None):
        print("convertToGrayScale")
        arr = np.frombuffer(image.imageData, dtype="uint8")
        image_grayscale = cv2.imdecode(arr, cv2.IMREAD_GRAYSCALE)
        _, image_grayscale_bytes = cv2.imencode(str(image.imageType), image_grayscale)
        return Image(
            imageType=image.imageType,
            imageData=image_grayscale_bytes
        )


if __name__ == '__main__':
    with Ice.initialize(sys.argv) as communicator:
        adapter = communicator.createObjectAdapterWithEndpoints("Adapter",
                                                                "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z")
        servant = OperationsI()
        adapter.add(servant, communicator.stringToIdentity("SimpleOperations"))
        adapter.activate()
        print("Server started")
        communicator.waitForShutdown()
