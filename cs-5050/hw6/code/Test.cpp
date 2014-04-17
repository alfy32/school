#include "Test.hpp"

const int RUNS = 30;

/////////////// Private ////////////////////

template<typename T>
double Test::averageRuntime(T t, int runs) {
  std::vector<double> times(runs);

  for (int i = 0; i < runs; i++) {
    clock_t start = clock();

    t();

    clock_t end = clock();

    times[i] = (end - start) / (CLOCKS_PER_SEC / 1000);
  }

  return avg(times);
}

double Test::avg(std::vector<double>& values) {
  double sum = 0;
  for (int i = 0; i < (int)values.size(); i++) {
    sum += values[i];
  }
  return sum / values.size();
}

///////////////// Public ////////////////////

void Test::test_all() {
  std::cout << "Running all tests..." << std::endl
    << std::endl;


  test_naive_works();
  test_bm_works();
  test_KMP_works();
}

///////////////// Tests ///////////////////

void Test::test_naive_works() {
  std::cout << "Naive: " << std::endl;

  StringMatch* stringMatch = new NaiveStringMatch;

  test_1(stringMatch);
  test_2(stringMatch);
  test_3(stringMatch);
  test_fail(stringMatch);

  delete stringMatch;
  std::cout << std::endl;
}

void Test::test_bm_works() {
  std::cout << "BM: " << std::endl;
  StringMatch* stringMatch = new BMStringMatch;

  test_1(stringMatch);
  test_2(stringMatch);
  test_3(stringMatch);
  test_fail(stringMatch);

  delete stringMatch;
  std::cout << std::endl;
}

void Test::test_KMP_works() {
  std::cout << "KMP: " << std::endl;
  StringMatch* stringMatch = new KMPStringMatch;

  test_1(stringMatch);
  test_2(stringMatch);
  test_3(stringMatch);
  test_fail(stringMatch);

  delete stringMatch;
  std::cout << std::endl;
}

void Test::test_1(StringMatch* stringMatch) {
  std::cout << "Test 1: ";

  std::string P = "bacbabababacaab";
  std::string T = "ababaca";

  stringMatch->preprocess(T);
  int match = stringMatch->match(P, T);

  if (match == StringMatch::NO_MATCH || match != 6) {
    std::cout << "Index: " << match << std::endl;
    std::cout << "************ FAILED ********************" << std::endl;
    return;
  }

  std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
  std::cout << match << ' ' << matchString << std::endl;
}

void Test::test_2(StringMatch* stringMatch) {
  std::cout << "Test 2: ";

  std::string P = "Something very long that has something in it that will be matched to something";
  std::string T = "something";

  stringMatch->preprocess(T);
  int match = stringMatch->match(P, T);

  if (match == StringMatch::NO_MATCH || match != 69) {
    std::cout << "Index: " << match << std::endl;
    std::cout << "************ FAILED ********************" << std::endl;
    return;
  }

  std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
  std::cout << match << ' ' << matchString << std::endl;
}

void Test::test_3(StringMatch* stringMatch) {
  std::cout << "Test 3: ";

  std::string P = "ababababaccccababaccababacc";
  std::string T = "ccababa";

  stringMatch->preprocess(T);
  int match = stringMatch->match(P, T);

  if (match == StringMatch::NO_MATCH || match != 18) {
    std::cout << "Index: " << match << std::endl;
    std::cout << "************ FAILED ********************" << std::endl;
    return;
  }

  std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
  std::cout << match << ' ' << matchString << std::endl;
}

void Test::test_fail(StringMatch* stringMatch) {
  std::cout << "Test Fail: ";

  std::string P = "bacbabababacaab";
  std::string T = "ababacab";

  stringMatch->preprocess(T);
  int match = stringMatch->match(P, T);

  std::cout << match << " No Match" << std::endl;

  if (match != StringMatch::NO_MATCH){
    std::cout << "************ FAILED ********************" << std::endl;
  }
}
