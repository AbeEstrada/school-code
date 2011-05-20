/*
    Orignal code
    http://cboard.cprogramming.com/cplusplus-programming/129819-cplusplus-parser-simplified-lexical-analyzer.html#post968919
*/

#include <iostream>
#include <string>

#include "homework.h" // Homework

#include "keywords.h"
#include "search.h"
#include "tokens.h"

int main() {
    std::string line;
    
    while(std::getline(std::cin, line)) {
        tokens(line); // print tokens
    }
    
    /* Homework
    std::cout << "========================" << std::endl;
    std::cout << "while: " << _while_counter << std::endl;
    std::cout << "if: " << _if_counter << std::endl;
    std::cout << "=: " << _equal_counter << std::endl;
    std::cout << "identifiers: " << _identifiers_counter << std::endl; */
    
    return 0;
}