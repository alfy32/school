#include "FFT.hpp"

int FFT::RBS(int i, int k) {
  // returns the bit reversed index of
  // input i, k that number of bits (log2 n)
  if(k == 0) return i;
  if(i%2 == 1)
    return pow(2.0, k-1) + RBS(i/2, k-1);
  else
    return RBS(i/2, k-1);
}

FFT::FFT() {

}

void FFT::preComputeOmega(int n) {
  omega = Vector<Complex> (n);

  for(int i = 0; i < n; i++) {
    omega[i] = Complex(cos(2*PI/n * i), sin(2*PI/n * i));
  }

  omega2n = Vector<Complex> (2*n);
  omega2nInverse = Vector<Complex> (2*n);

  for(int i = 0; i < 2*n; ++i) {
    omega2n[i] = Complex(cos(2*PI/(2*n) * i), sin(2*PI/(2*n) * i));
    omega2nInverse[i] = Complex(cos(2*PI/(2*n) * i), sin(-2*PI/(2*n) * i));
  }
}

Vector<Complex> FFT::algebraic(Vector<Complex> P, int n, Vector<Complex>& x) {
  Vector<Complex> answer;

  for(int xIndex = 0; xIndex < n; xIndex++) {

    std::cout << "X: " << x[xIndex] << std::endl;

    Complex curr = P.back();

    for(int i = P.size()-2; i >= 0; --i) {
      curr = P[i] + x[xIndex] * curr;
    }
    std::cout << curr << std::endl;
    answer.push_back(curr);
  }

  return answer;
}

Vector<Complex> FFT::recursive(Vector<Complex> P, int n, Vector<Complex>& x, int power) {
  // Allocate Memory
  Vector<Complex> PolyE(n/2);
  Vector<Complex> PolyO(n/2);
  Vector<Complex> Poly(n);

  // base case
  if(n == 1) {
    Poly[0] = P[0];
    return Poly;
  }

  // shuffle odd/even into PolyE[] and PolyO[]
  for(int i = 0; i < n/2; i++) {
    PolyE[i] = P[2*i];
    PolyO[i] = P[2*i + 1];
  }

  // call recursion on 2 x 1/2
  Vector<Complex> SolutionE = recursive(PolyE, n/2, x, power*2);
  Vector<Complex> SolutionO = recursive(PolyO, n/2, x, power*2);

  // solution construction
  for(int i = 0; i < n/2; i++) {
    Poly[i] = SolutionE[i] + x[i*power] * SolutionO[i];
    Poly[i+n/2] = SolutionE[i] - x[i*power] * SolutionO[i];
  }

  return Poly;
}

Vector<Complex> FFT::dynamic(Vector<Complex> Poly, int n, Vector<Complex>& x) {
  int logN = log2(n);
  Vector<Vector<Complex>> sol(2/*logN+1*/, Vector<Complex>(n));

  for(int i = 0; i < n; i++){
    sol[0][RBS(i, logN)] = Poly[i];
  }


  // solution array initialized with base case

  int power = n/2; // of omega slot at bottom
  int size = 2; // smallest problem size

  // scan from bottom to top
  for(int k = 1; k <= logN; k++) {
    // scan across by size for each subsolution
    for(int i = 0; i < n; i+= size) {
      // fills the solution
      for(int j = 0; j < size/2; j++) {
        Complex odd = x[j*power]*sol[(k-1) %2][i+j+size/2];
        // + 1/2 solution
        sol[k %2][i+j] = sol[(k-1) %2][i+j] + odd;
        // - 1/2 solution
        sol[k %2][i+j+size/2] = sol[(k-1) %2][i+j] - odd;
      }
    }
    // decrease power (more up)
    power = power/2;
    // increase size
    size = size*2;
  }

  return sol[(logN) %2];
}

Vector<Complex> FFT::polyMultR(Vector<Complex> P, Vector<Complex> Q, int n) {

  // (a) Pad with zeros
  P.resize(2*n, 0);
  Q.resize(2*n, 0);

  // (b) Call FFT
  Vector<Complex> PFFT = recursive(P, 2*n, omega2n, 1);
  Vector<Complex> QFFT = recursive(Q, 2*n, omega2n, 1);


  // (c) Multiply the values
  Vector<Complex> sol(2*n);


  for(int i = 0; i < 2*n; ++i) {
    sol[i] = PFFT[i] * QFFT[i];
  }


  // (d) Call inverse FFT
  Vector<Complex> answer = recursive(sol, 2*n, omega2nInverse, 1);


  // + Note must divide by 1/(2n)
  for(int i = 0; i < 2*n; ++i) {
    answer[i] /= 2*n;
  }

  return answer;
}

Vector<Complex> FFT::polyMultD(Vector<Complex> P, Vector<Complex> Q, int n) {

  // (a) Pad with zeros
  P.resize(2*n, 0);
  Q.resize(2*n, 0);

  // (b) Call FFT
  Vector<Complex> PFFT = dynamic(P, 2*n, omega2n);
  Vector<Complex> QFFT = dynamic(Q, 2*n, omega2n);
  // (c) Multiply the values
  Vector<Complex> sol(2*n);

  for(int i = 0; i < 2*n; ++i) {
    sol[i] = PFFT[i] * QFFT[i];
  }

  // (d) Call inverse FFT
  Vector<Complex> answer = dynamic(sol, 2*n, omega2nInverse);

  // + Note must divide by 2n
  for(int i = 0; i < answer.size(); ++i) {
    answer[i] /= 2*n;
  }

  return answer;
}
