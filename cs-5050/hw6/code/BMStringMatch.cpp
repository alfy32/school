#include "BMStringMatch.hpp"

// From: http://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string_search_algorithm
// Converted from Java to C++ by me.

/**
* Makes the jump table based on the mismatched character information.
*/
void BMStringMatch::makeCharTable(std::string& needle, int alphabetSize) {
  charTable.resize(alphabetSize, 0);
  for (int i = 0; i < (int)charTable.size(); ++i) {
    charTable[i] = needle.length();
  }
  for (int i = 0; i < (int)needle.length() - 1; ++i) {
    charTable[needle[i]] = needle.length() - 1 - i;
  }
}

/**
* Makes the jump table based on the scan offset which mismatch occurs.
*/
void BMStringMatch::makeOffsetTable(std::string& needle) {
  offsetTable.resize(needle.length(), 0);
  int lastPrefixPosition = needle.length();
  for (int i = needle.length() - 1; i >= 0; --i) {
    if (isPrefix(needle, i + 1)) {
      lastPrefixPosition = i + 1;
    }
    offsetTable[needle.length() - 1 - i] = lastPrefixPosition - i + needle.length() - 1;
  }
  for (int i = 0; i < (int)needle.length() - 1; ++i) {
    int slen = suffixLength(needle, i);
    offsetTable[slen] = needle.length() - 1 - i + slen;
  }
}

/**
* Is needle[p:end] a prefix of needle?
*/
bool BMStringMatch::isPrefix(std::string needle, int p) {
  for (int i = p, j = 0; i < (int)needle.length(); ++i, ++j) {
    if (needle[i] != needle[j]) {
      return false;
    }
  }
  return true;
}

/**
* Returns the maximum length of the substring ends at p and is a suffix.
*/
int BMStringMatch::suffixLength(std::string needle, int p) {
  int len = 0;
  for (int i = p, j = needle.length() - 1;
    i >= 0 && needle[i] == needle[j]; --i, --j) {
    len += 1;
  }
  return len;
}

void BMStringMatch::preprocess(std::string& needle) {
  makeCharTable(needle, 256);
  makeOffsetTable(needle);
}

int BMStringMatch::match(std::string& haystack, std::string& needle)
{
  int matchIndex = NO_MATCH;

  if (needle.length() == 0) return NO_MATCH;

  for (int i = needle.length() - 1, j; i < (int)haystack.length();) {
    for (j = needle.length() - 1; needle[j] == haystack[i]; --i, --j) {
      if (j == 0) {
        matchIndex = i;
        break;
      }
    }
    i += std::max(offsetTable[needle.length() - 1 - j], charTable[haystack[i]]);
  }

  return matchIndex;
}