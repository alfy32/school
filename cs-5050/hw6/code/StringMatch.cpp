#include "StringMatch.hpp"

std::string StringMatch::readFullFile(std::string fileName) {
  std::ifstream inFile("strings/shakespeare.txt");
  std::string fullFile;

  std::getline(inFile, fullFile, (char)-1);
  inFile.close();
  
  return fullFile;
}