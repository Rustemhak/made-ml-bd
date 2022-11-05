import breeze.linalg.{DenseMatrix, DenseVector, csvread, csvwrite, sum}
import scala.math.floor
import java.io.File


object Main {


  def MSE(yTrue: DenseVector[Double], yPred: DenseVector[Double]): Double = {
    sum((yPred - yTrue).map(y => y * y)) * (1.0 / yTrue.size)
  }


  val N_FOLDS = 9
  val TRAIN_PATH = "insurance_train.csv"
  val TEST_PATH = "insurance_test.csv"
  val OUTPUT_PATH = "prediction.csv"

  def main(args: Array[String]): Unit = {
    val model = new LinearRegression()
    val trainData: DenseMatrix[Double] = csvread(new File(TRAIN_PATH), ',', skipLines = 1)
    val testData: DenseMatrix[Double] = csvread(new File(TEST_PATH), ',', skipLines = 1)
    val x = trainData(::, 0 until trainData.cols - 1)
    val y = trainData(::, -1).toDenseVector

    val foldSize = floor(trainData.rows / N_FOLDS).toInt
    for (i <- 0 until N_FOLDS) {
      val testInd = i * foldSize until (i + 1) * foldSize - 1
      val trainInd: IndexedSeq[Int] = (0 until testInd.start) ++ (testInd.end until (trainData.rows - 1))

      model.fit(x(trainInd, ::).toDenseMatrix, y(trainInd).toDenseVector)
      val yPred = model.predict(x(testInd, ::).toDenseMatrix)
      val mse = MSE(y(testInd), yPred)

      println(f"MSE for fold ${i + 1}/${N_FOLDS}: ${mse}\n")
    }
    model.fit(x, y)
    val yTestPred = model.predict(testData(::, 0 until testData.cols - 1))
    val yTestTrue = testData(::, -1).toDenseVector
    val mse = MSE(yTestTrue, yTestPred)
    println(f"MSE for test: ${mse}\n")
    csvwrite(new File(OUTPUT_PATH), yTestPred.toDenseMatrix.t)
  }
}

