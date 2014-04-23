#include <string>

#include "Test.hpp"

StringMatch* getAlgorithm(std::string which) {
  if (which == "naive") return new NaiveStringMatch;
  if (which == "bm") return new BMStringMatch;
  if (which == "kmp") return new KMPStringMatch;
  else return NULL;
}

int main(int argc, char** argv) {
  srand((unsigned)time(0));
  
  if(argc >= 2 && std::string(argv[1]) == "test")
  {
    std::string which = argv[2];

    if (which == "all") Test::test_all();
    else if (which == "naive") Test::test_naive_works();
    else if (which == "bm") Test::test_bm_works();
    else if (which == "kmp") Test::test_kmp_works();
  }

  else if(argc >= 4 && std::string(argv[1]) == "run")
  {
    std::string which = argv[3];

    if (which == "shakespeare") Test::run_shakespeare(getAlgorithm(argv[2]));
    else if (which == "binary") Test::run_binary(getAlgorithm(argv[2]), std::stoi(argv[4]));
    else if (which == "size") Test::run_patternSize(getAlgorithm(argv[2]), std::stoi(argv[4]));
  }

  return 0;
}