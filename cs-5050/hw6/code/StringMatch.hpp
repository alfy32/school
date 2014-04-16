#ifndef STRING_MATCH_HPP
#define STRING_MATCH_HPP

#include <string>

class StringMatch 
{
private:

public:
  static const int NO_MATCH = -1;

  virtual void preprocess(std::string& T) = 0;
  virtual int match(std::string& P, std::string& T) = 0;
};

#endif