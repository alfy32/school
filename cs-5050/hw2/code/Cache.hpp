#ifndef CACHE_HPP
#define CACHE_HPP

#ifndef NOT_SEEN
#define NOT_SEEN -1
#endif

class Cache {

public:

  virtual void reset() = 0;

  virtual int get(int row, int col) = 0;
  virtual void set(int row, int col, int value) = 0;

  virtual bool seen(int row, int col) = 0;

  virtual void print() = 0; 
};

#endif