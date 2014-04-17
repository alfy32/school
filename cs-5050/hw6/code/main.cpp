#include <string>

#include "Test.hpp"

int main(int argc, char** argv) {
  srand((unsigned)time(0));

  if(argc >= 2 && std::string(argv[1]) == "test")
  {
    std::string which = argv[2];

    if (which == "all") Test::test_all();
    else if (which == "naive") Test::test_naive_works();
    else if (which == "bm") Test::test_bm_works();
    else if (which == "kmp") Test::test_KMP_works();
  }

  else if(argc == 3 && std::string(argv[1]) == "run")
  {
    std::string which = argv[2];

  }

  return 0;
}