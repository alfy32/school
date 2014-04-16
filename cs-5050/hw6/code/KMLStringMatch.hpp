#ifndef KML_STRING_MATCH_HPP
#define KML_STRING_MATCH_HPP

#include <string>
#include <vector>

#include "StringMatch.hpp"

class KMLStringMatch : public StringMatch
{
private:
  std::vector<int> a;
public:
  virtual void preprocess(std::string& T);
  virtual int match(std::string& P, std::string& T);
};

#endif