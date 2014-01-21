#include <iostream>
#include <string>

#include <ctime>

#include "knapsack.hpp"
#include "test.hpp"

int main(int argc, char* argv[]) {
  if(argc >= 2 && std::string(argv[1]) == "test1") {
    int runs        = argc > 2 ? std::stoi(argv[2]) : 30;
    int bagSize     = 1000;
    int minItemSize = 1;
    int maxItemSize = argc > 4 ? std::stoi(argv[3]) : 1000;
    int minValue    = argc > 5 ? std::stoi(argv[4]) : 1;
    int maxValue    = argc > 6 ? std::stoi(argv[5]) : 100;
    int minN        = 6;
    int maxN        = argc > 7 ? std::stoi(argv[6]) : 1000;

    Test::test1(
      runs, 
      bagSize, 
      minItemSize, maxItemSize,
      minValue, maxValue
    );

  }
  else if(argc == 4 && std::string(argv[1]) == "test2") {
    std::string test(argv[2]);
    int runs = std::stoi(argv[3]);

    if(test == "dynamic") {
      Test::test2dynamic(
        runs
      );  
    }  
    else if(test == "caching") {
      Test::test2caching(
        runs
      );  
    }

  }
  else {
    std::cout << "Nothing" << std::endl;
  }
  
  return 0;
}
