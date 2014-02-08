#include <string>

#include "Knapsack.hpp"
#include "CacheRegular.hpp"
#include "Test.hpp"

int main(int argc, char** argv) {
  if(argc >= 2 && std::string(argv[1]) == "test")
  {
    if(argc == 2) Test::run("All");
    else Test::run(argv[2]);

    if(argc == 4)
      Test::showTraceback(std::stoi(argv[2]), std::stoi(argv[3]));
  }
}