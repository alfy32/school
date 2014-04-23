#include "StringMatch.hpp"

std::string StringMatch::readFullFile(std::string fileName) {
  std::ifstream inFile("strings/shakespeare.txt");
  std::string fullFile;

  std::getline(inFile, fullFile, (char)-1);
  inFile.close();

  return fullFile;
}

std::string StringMatch::generateArtificialString(int length, int p) {
  std::string generatedString(length, '0');

  int ALPHABET_SIZE = 2;
  char characters[] = { '0', '1' };
  int curr = 0;

  for (char& ch : generatedString) {
    if ((rand() % 100 + 1) > p) {
      curr = (curr + 1) % ALPHABET_SIZE;
    }
    ch = characters[curr];
  }

  return generatedString;
}

std::string StringMatch::generateRandomAlphaString(int length) {
  std::string generatedString(length, 'a');

  for (char& ch : generatedString) 
    ch += rand() % 26;

  return generatedString;
}