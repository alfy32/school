#include <string>

#include "Test.hpp"

int main(int argc, char** argv) {
  if(argc >= 2 && std::string(argv[1]) == "test")
  {
    if(argc == 2) Test::test("All");
    else Test::test(argv[2]);
  }

  if(argc == 3 && std::string(argv[1]) == "run")
  {
    Test::run(argv[2]);
  }

  return 0;
}