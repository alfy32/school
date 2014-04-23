#ifndef STRING_MATCH_HPP
#define STRING_MATCH_HPP

#include <string>
#include <fstream>
#include <ctime>

class StringMatch 
{
private:

public:
  static const int NO_MATCH = -1;
  
  static std::string readFullFile(std::string fileName);
  static std::string generateArtificialString(int length, int p);
  static std::string generateRandomAlphaString(int length);

  StringMatch() { srand((unsigned)time(NULL)); }

  virtual void preprocess(std::string& T) = 0;
  virtual int match(std::string& P, std::string& T) = 0;
};

#endif