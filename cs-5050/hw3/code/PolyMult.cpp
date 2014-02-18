#include "PolyMult.hpp"

PolyMult::PolyMult() {
  srand(time(NULL));
}

std::vector<double> PolyMult::getRandomVector(int size, int min, int max) {
  std::vector<double> random(size, 0.0);
  std::generate(random.begin(), random.end(), []{ return rand()*(max-min) + min; });
  return random;
}


std::vector<double> PolyMult::schoolBook(int n, std::vector<double>& P, std::vector<double>& Q) {
  std::vector<double> PQ(2*n-1, 0.0);

  for(int p = 0; p < n; ++p) {
    for(int q = 0; q < n; ++q) {
      PQ[p+q] += P[p] * Q[q];
    }
  }

  return PQ;
}

std::vector<double> PolyMult::polyMult(int n, std::vector<double>& P, std::vector<double>& Q) {
  std::vector<double> PQ(2*n-1, 0.0);

  std::vector<double> PL(n/2, 0.0);
  std::vector<double> PH(n/2, 0.0);
  std::vector<double> PLandPH(n/2, 0.0);
  std::vector<double> QLandQH(n/2, 0.0);

  for(int i = 0; i < n/2; ++i) {
    PL[i] = P[i];
    PH[i] = P[i+n/2];
  }

  for(int i = 0; i < n/2; ++i) {
    PLandPH[i] = PL[i] + PH[i];
    QLandQH[i] = QL[i] + QH[i];
  }

  PLQL = polyMult(PL, QL);
  PHQH = polyMult(PH, QH);
  PQSum = polyMult(PLandPH, QLandQH);

  for(int i = 0; i < n; ++i) {
    PQ[i] += PLQL[i];
    PQ[i+n/2] += PQSum[i] - PLQL[i] - PHQH[i];
    PQ[i+n] += PHQH[i];
  }

  return PQ;
}

std::vector<double> PolyMult::divideAndConquer3(int n, std::vector<double>& P, std::vector<double>& Q) {
  std::vector<double> PQ(2*n-1, 0.0);

  std::vector<double> PL(n/2, 0.0);
  std::vector<double> PH(n/2, 0.0);
  std::vector<double> PLandPH(n/2, 0.0);
  std::vector<double> QLandQH(n/2, 0.0);

  for(int i = 0; i < n/2; ++i) {
    PL[i] = P[i];
    PH[i] = P[i+n/2];
  }

  for(int i = 0; i < n/2; ++i) {
    PLandPH[i] = PL[i] + PH[i];
    QLandQH[i] = QL[i] + QH[i];
  }

  PLQL = polyMult(PL, QL);
  PHQH = polyMult(PH, QH);
  PQSum = polyMult(PLandPH, QLandQH);

  for(int i = 0; i < n; ++i) {
    PQ[i] += PLQL[i];
    PQ[i+n/2] += PQSum[i] - PLQL[i] - PHQH[i];
    PQ[i+n] += PHQH[i];
  }

  return PQ;
}

//////////// Print ////////////////

void PolyMult::print() {
  std::cout << "P:" << '\t';

  for(double coeff : P) {
    std::cout << coeff << '\t';
  }

  std::cout << std::endl
            << "Q:" << '\t';

  for(double coeff : Q) {
    std::cout << coeff << '\t';
  }

  std::cout << std::endl
            << "PQ:" << '\t';

  for(double coeff : PQ) {
    std::cout << coeff << '\t';
  }

  std::cout << std::endl;
}