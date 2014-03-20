#ifndef FFT_HPP
#define FFT_HPP

#include <vector>
#include <iostream>
#include <complex>

#define PI 3.1415926
#define Complex std::complex<double>
#define Vector std::vector

class FFT {
private:
  Vector<Complex> omega, omega2n, omega2nInverse;

  int RBS(int i, int k);

public:
  FFT();

  void preComputeOmega(int n);
  Vector<Complex> algebraic(Vector<Complex> P, int n, Vector<Complex>& x);
  Vector<Complex> recursive(Vector<Complex> P, int n, Vector<Complex>& x, int power);
  Vector<Complex> dynamic(Vector<Complex> Poly, int n, Vector<Complex>& x);

  Vector<Complex> polyMultR(Vector<Complex> P, Vector<Complex> Q, int n);
  Vector<Complex> polyMultD(Vector<Complex> P, Vector<Complex> Q, int n);
};

#endif