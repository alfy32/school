#include <string>

#include "Test.hpp"

int main(int argc, char** argv) {
  srand(time(0));

  if(argc >= 2 && std::string(argv[1]) == "test")
  {
    if(argc == 2) Test::test("All");
    else Test::test(argv[2]);
  }

  else if(argc == 3 && std::string(argv[1]) == "run")
  {
    Test::run(argv[2]);
  }

  return 0;
}