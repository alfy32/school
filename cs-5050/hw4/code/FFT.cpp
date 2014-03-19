#include "FFT.hpp"

int FFT::RBS(int i, int k) {
  // returns the bit reversed index of
  // input i, k that number of bits (log2 n)
  if(k == 0) return i;
  if(i%2 == 1)
    return /*expt(2, k-1) +*/ RBS(i/2, k-1);
  else
    return RBS(i/2, k-1);
}

FFT::FFT() {

}

void FFT::preComputeOmega(int n) {
  omega = ComplexVector (n);

  for(int i = 0; i < n; i++) {
    omega[i] = std::complex<double>(cos(2*PI/n * i), sin(2*PI/n * i));
  }
}

ComplexVector FFT::recursive(ComplexVector P, int n, int power) {
  // Allocate Memory
  ComplexVector PolyE(n/2);
  ComplexVector PolyO(n/2);
  ComplexVector Poly(n);

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
  ComplexVector SolutionE = recursive(PolyE, n/2, power*2);
  ComplexVector SolutionO = recursive(PolyO, n/2, power*2);

  // solution construction
  for(int i = 0; i < n/2; i++) {
    Poly[i] = SolutionE[i] + omega[power] * SolutionO[i];
    Poly[i+n/2] = SolutionE[i] - omega[power] * SolutionO[i];
  }

  return Poly;
}

ComplexVector FFT::dynamic(ComplexVector Poly, int n) {
  int logN = log2(n);
  Vector<Vector<Complex>> sol(logN+1, Vector<Complex>(n));

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
        Complex odd = omega[j*power]*sol[k-1][i+j+size/2];
        // + 1/2 solution
        sol[k][i+j] = sol[k+1][i+j] + odd;
        // - 1/2 solution
        sol[k][i+j+size/2] = sol[k-1][i+j] - odd;
      }
      // decrease power (more up)
      power = power/2;
      // increase size
      size = size*2;
    }
  }
}

ComplexVector FFT::polyMult(ComplexVector P, ComplexVector Q, int n) {

  // (a) Pad with zeros
  P.resize(2*n, 0);
  Q.resize(2*n, 0);

  // (b) Call FFT
  recursive(P, n, 1);
  recursive(Q, n, 1);

  // (c) Multiply the values


  // (d) Call inverse FFT + Note must divide by 1/(2n)

}
