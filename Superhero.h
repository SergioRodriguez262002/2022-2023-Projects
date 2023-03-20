// g++ heromain.cc Superhero.cc -std=c++11 -o hero
// The c++11 is to switch to the 2011 defns so we can use "auto"
#ifndef SUPERHERO_H
#define SUPERHERO_H
#include <iostream>
#include <vector>
#include <string>


class Superhero {     // The class
 public:
  std::string* firstName;
  std::string* lastName;
  std::string* heroName;
  Superhero();
  Superhero(std::string* firstName, std::string* lastName, std::string* heroName);
  virtual ~Superhero();
  std::string getHeroName();
  Superhero(const Superhero& sp);
  friend std::ostream& operator<<(std::ostream& os, const Superhero& sp);
  void  operator=(const Superhero& sp);
  friend bool operator==(const Superhero& a, const Superhero& b);
  friend bool operator<(const Superhero& a, const Superhero& b);
};
#endif
