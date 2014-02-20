#include "PolyMult.hpp"

PolyMult::PolyMult() {
  srand(time(NULL));
}

std::vector<double> PolyMult::makeRandomVector(int n, int min, int max) {
  std::vector<double> random(n);
  std::generate(random.begin(), random.end(), [=](){
    return rand() * (max-min) + min;
  });
  return random;
}

std::vector<double> PolyMult::schoolBook(int n, std::vector<double> P, std::vector<double> Q) {
  std::vector<double> PQ(2*n-1, 0.0);

  for(int p = 0; p < P.size(); ++p) {
    for(int q = 0; q < Q.size(); ++q) {
      PQ[p+q] += P[p] * Q[q];
    }
  }

  return PQ;
}

std::vector<double> PolyMult::divideAndConquer4(int n, std::vector<double> P, std::vector<double> Q) {
  if(n == 1) {
    return std::vector<double>(1, P[0] * Q[0]);
  }

  std::vector<double> PL(n/2, 0.0);
  std::vector<double> PH(n/2, 0.0);

  std::vector<double> QL(n/2, 0.0);
  std::vector<double> QH(n/2, 0.0);

  for(int i = 0; i < n/2; ++i) {
    PL[i] = P[i];
    PH[i] = P[i+n/2];

    QL[i] = Q[i];
    QH[i] = Q[i+n/2];
  }

  std::vector<double> PLQL = divideAndConquer4(n/2, PL, QL);
  std::vector<double> PHQH = divideAndConquer4(n/2, PH, QH);
  std::vector<double> PLQH = divideAndConquer4(n/2, PL, QH);
  std::vector<double> PHQL = divideAndConquer4(n/2, PH, QL);

  std::vector<double> PQ(2*n-1, 0.0);

  for(int i = 0; i < n-1; ++i) {
    PQ[i] += PLQL[i];
    PQ[i+n/2] += PLQH[i];
    PQ[i+n/2] += PHQL[i];
    PQ[i+n] += PHQH[i];
  }

  return PQ;
}

std::vector<double> PolyMult::divideAndConquer3(int n, std::vector<double> P, std::vector<double> Q) {
  if(n == 1) {
    return std::vector<double>(1, P[0] * Q[0]);
  }

  std::vector<double> PL(n/2, 0.0);
  std::vector<double> PH(n/2, 0.0);

  std::vector<double> QL(n/2, 0.0);
  std::vector<double> QH(n/2, 0.0);

  for(int i = 0; i < n/2; ++i) {
    PL[i] = P[i];
    PH[i] = P[i+n/2];

    QL[i] = Q[i];
    QH[i] = Q[i+n/2];
  }

  std::vector<double> PLandPH(n/2, 0.0);
  std::vector<double> QLandQH(n/2, 0.0);

  for(int i = 0; i < n/2; ++i) {
    PLandPH[i] = PL[i] + PH[i];

    QLandQH[i] = QL[i] + QH[i];
  }

  std::vector<double> PLQL = divideAndConquer3(n/2, PL, QL);
  std::vector<double> PHQH = divideAndConquer3(n/2, PH, QH);
  std::vector<double> PQSum = divideAndConquer3(n/2, PLandPH, QLandQH);

  std::vector<double> PQ(2*n-1, 0.0);

  for(int i = 0; i < n-1; ++i) {
    PQ[i] += PLQL[i];
    PQ[i+n/2] += PQSum[i] - PLQL[i] - PHQH[i];
    PQ[i+n] += PHQH[i];
  }

  return PQ;
}

//////////// Print ////////////////

void PolyMult::print(int n, std::vector<double>& P, std::vector<double>& Q, std::vector<double>& PQ) {
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