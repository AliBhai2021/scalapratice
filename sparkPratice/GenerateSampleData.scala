package sparkPratice

import java.io.FileWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.util.Random

object GenerateSampleData extends App{
  val filePath = "data/sample_product_sales.csv"
  val numberOfRecords = 1000

  val products = List(
    ("P001", "Laptop", "Electronics"),
    ("P002", "Smartphone", "Electronics"),
    ("P003", "Tablet", "Electronics"),
    ("P004", "Headphones", "Accessories"),
    ("P005", "Keyboard", "Accessories"),
    ("P006", "Mouse", "Accessories"),
    ("P007", "Monitor", "Electronics"),
    ("P008", "Printer", "Electronics"),
    ("P009", "Camera", "Electronics"),
    ("P010", "Speaker", "Accessories")
  )
  val cities = List("HYD","BNG","CNI","DLH","MB","KK","PN","MNG")

  val random = new Random()
  val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  val header = "ProductID,ProductName,Category,QuantitySold,Price,SaleDate,City"
  val writer = new FileWriter(filePath)
  writer.write(header + "\n")

  for (_ <- 1 to numberOfRecords) {
    val (productID, productName, category) = products(random.nextInt(products.size))
    val quantitySold = random.nextInt(100) + 1
    //val price = if (random.nextBoolean()) (random.nextDouble() * 1000).formatted("%.2f") else "null"
    val price = (random.nextDouble() * 1000).formatted("%.2f")
    //val saleDate = if (random.nextBoolean()) LocalDate.now().minusDays(random.nextInt(365)).format(dateFormatter) else "null"
    val saleDate = LocalDate.now().minusDays(random.nextInt(365)).format(dateFormatter)
    //val city = cities(random.nextInt(cities.size))
    val city = if (random.nextBoolean()) cities(random.nextInt(cities.size)) else "null"
    val record = s"$productID,$productName,$category,$quantitySold,$price,$saleDate,$city"
    writer.write(record + "\n")
  }

  writer.close()
  println(s"Sample data generated and written to $filePath")
}
