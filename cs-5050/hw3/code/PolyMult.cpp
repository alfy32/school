#include "PolyMult.hpp"

PolyMult::PolyMult(int n) {
  srand(time(NULL));

  P = std::vector<double>(n, 0.0);
  Q = std::vector<double>(n, 0.0);

  int min = -1, max = 1;

  std::generate(P.begin(), P.end(), [=]{
    return rand()*(max-min) + min;
  });

  std::generate(Q.begin(), Q.end(), [=]{
    return rand()*(max-min) + min;
  });

  PQ = std::vector<double>(2*n-1, 0.0);
}

PolyMult::PolyMult(int n, double p[], double q[]) {
  srand(time(NULL));

  P = std::vector<double>(p, p+n);
  Q = std::vector<double>(q, q+n);

  PQ = std::vector<double>(2*n-1, 0.0);
}


void PolyMult::schoolBook() {
  for(int p = 0; p < P.size(); ++p) {
    for(int q = 0; q < Q.size(); ++q) {
      PQ[p+q] += P[p] * Q[q];
    }
  }
}

void PolyMult::divideAndConquer4(int pLow, int pHigh, int qLow, int qHigh) {
  if(pHigh < pLow || qHigh < qLow) return;
  if(pLow == pHigh && qLow == qHigh) {
    PQ[pLow + qLow] += P[pLow] * Q[qLow];
    return;
  }

  int pMid = (pHigh - pLow)/2 + pLow;
  int qMid = (qHigh - qLow)/2 + qLow;

  // std::cout << "P: " << pLow << '-' << pMid << '-' << pHigh << ' '
  //           << "Q: " << qLow << '-' << qMid << '-' << qHigh << ' '
  //           << std::endl;

  divideAndConquer4(pLow, pMid, qLow, qMid);
  divideAndConquer4(pMid+1, pHigh, qMid+1, qHigh);
  divideAndConquer4(pLow, pMid, qMid+1, qHigh);
  divideAndConquer4(pMid+1, pHigh, qLow, qMid);
}

void PolyMult::divideAndConquer3(int pLow, int pHigh, int qLow, int qHigh) {
  if(pHigh < pLow || qHigh < qLow) return;
  if(pLow == pHigh && qLow == qHigh) {
    PQ[pLow + qLow] += P[pLow] * Q[qLow];
    return;
  }

  int pMid = (pHigh - pLow)/2 + pLow;
  int qMid = (qHigh - qLow)/2 + qLow;

  divideAndConquer4(pLow, pMid, qLow, qMid);
  divideAndConquer4(pMid+1, pHigh, qMid+1, qHigh);
  divideAndConquer4(pLow, pMid, qMid+1, qHigh);
  divideAndConquer4(pMid+1, pHigh, qLow, qMid);
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