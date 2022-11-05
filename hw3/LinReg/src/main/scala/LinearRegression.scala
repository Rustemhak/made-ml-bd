import breeze.linalg.{DenseMatrix, DenseVector, sum}

class LinearRegression() {
  var weights = new DenseVector[Double](0)
  var bias = 0.0

  def fit(x: DenseMatrix[Double], y: DenseVector[Double], lr: Double = 1e-4, numIter: Int = 1e4.toInt) {
    val m = x.rows
    weights = DenseVector.zeros(x.cols)
    for (_ <- 0 to numIter) {
      val yPred = predict(x)
      val dW = -2.0 / m * (x.t * (y - yPred))
      val dB = -2.0 / m * sum(y - yPred)
      weights -= (dW * lr)
      bias -= dB * lr
    }

  }

  def predict(x: DenseMatrix[Double]): DenseVector[Double] = {
    (x * weights + bias).toDenseVector
  }
}

