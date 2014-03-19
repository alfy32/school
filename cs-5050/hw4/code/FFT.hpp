#ifndef FFT_HPP
#define FFT_HPP

#include <vector>
#include <iostream>
#include <complex>

#define PI 3.1415926
#define ComplexVector std::vector<std::complex<double>>
#define Complex std::complex<double>
#define Vector std::vector

class FFT {
private:
  ComplexVector omega;

  int RBS(int i, int k);

public:
  FFT();

  void preComputeOmega(int n);
  ComplexVector recursive(ComplexVector P, int n, int power);
  ComplexVector dynamic(ComplexVector Poly, int n);

  ComplexVector polyMult(ComplexVector P, ComplexVector Q, int n);
};

#endif