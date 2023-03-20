// g++ heromain.cc Superhero.cc -std=c++11 -o hero
// The c++11 is to switch to the 2011 defns so we can use "auto"

#include <iostream>
#include <vector>
#include <string>
#include "Superhero.h"

Superhero::Superhero(std::string* firstName, std::string* lastName, std::string* heroNam\
e){
  this->firstName = new std::string(*firstName);
  this->lastName = new std::string(*lastName);
  this->heroName = new std::string(*heroName);
}

Superhero::~Superhero(){}

std::string  Superhero::getHeroName(){
  return *heroName;
}

Superhero::Superhero(const Superhero& sp){
  this->firstName = new std::string(*sp.firstName);
  this->lastName = new std::string(*sp.lastName);
  this->heroName = new std::string(*sp.heroName);
}

std::ostream& operator<<(std::ostream& os, const Superhero& sp)
{
  os << *sp.firstName << ' ' << *sp.lastName;
    return os;
}

void Superhero::operator = (const Superhero& sp){
  firstName = sp.firstName;
  lastName = sp.lastName;
  heroName = sp.heroName;
 }

bool operator==(const Superhero& a, const Superhero& b){
  return *a.firstName == *b.firstName && *a.lastName == *b.lastName;
}

bool operator<(const Superhero& a, const Superhero& b){
  return *a.lastName < *b.lastName;
}
