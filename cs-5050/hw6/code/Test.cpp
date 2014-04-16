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
}

///////////////// Tests ///////////////////

void Test::test_naive_works() {
  std::cout << "Match to find..." << std::endl << std::endl;
  {
    StringMatch* stringMatcher = new NaiveStringMatch;

    std::string P = "Something very long that has something in it that will be matched to something";
    std::string T = "something";

    int match = stringMatcher->match(P, T);
    std::cout << match << ' ';

    if (match != StringMatch::NO_MATCH) {
      std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
      std::cout << matchString << std::endl;

      if (match == 69 && matchString == T) {
        std::cout << "Test Passed" << std::endl;
      }
      else {
        std::cout << "************ FAILED ********************" << std::endl;
      }
    }



    std::cout << std::endl;

    delete stringMatcher;
  }
  std::cout << "No Match..." << std::endl << std::endl;
  {
    StringMatch* stringMatcher = new NaiveStringMatch;

    std::string P = "Something very long that has Something in it that will be matched to Something";
    std::string T = "ring";

    int match = stringMatcher->match(P, T);
    std::cout << match << ' ';

    if (match != StringMatch::NO_MATCH) {
      std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
      std::cout << matchString << std::endl;
    }

    if (match == StringMatch::NO_MATCH) {
      std::cout << "Test Passed" << std::endl;
    }
    else {
      std::cout << "************ FAILED ********************" << std::endl;
    }

    std::cout << std::endl;

    delete stringMatcher;
  }
}

void Test::test_bm_works() {

}

void Test::test_kml_works() {
  std::cout << "Match to find..." << std::endl << std::endl;
  {
    StringMatch* stringMatcher = new KMLStringMatch;

    std::string P = "bacbabababacaab";
    std::string T = "ababaca";

    stringMatcher->preprocess(T);
    int match = stringMatcher->match(P, T);
    std::cout << match << ' ';

    if (match != StringMatch::NO_MATCH) {
      std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
      std::cout << matchString << std::endl;

      if (match == 69 && matchString == T) {
        std::cout << "Test Passed" << std::endl;
      }
      else {
        std::cout << "************ FAILED ********************" << std::endl;
      }
    }



    std::cout << std::endl;

    delete stringMatcher;
  }
  std::cout << "No Match..." << std::endl << std::endl;
  {
    StringMatch* stringMatcher = new KMLStringMatch;

    std::string P = "bacbabababacaab";
    std::string T = "ababaca";

    stringMatcher->preprocess(T);
    int match = stringMatcher->match(P, T);
    std::cout << match << ' ';

    if (match != StringMatch::NO_MATCH) {
      std::string matchString = std::string(P.begin() + match, P.begin() + match + T.length());
      std::cout << matchString << std::endl;
    }

    if (match == StringMatch::NO_MATCH) {
      std::cout << "Test Passed" << std::endl;
    }
    else {
      std::cout << "************ FAILED ********************" << std::endl;
    }

    std::cout << std::endl;

    delete stringMatcher;
  }
}