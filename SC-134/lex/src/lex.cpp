/*
    Orignal code
    http://cboard.cprogramming.com/cplusplus-programming/129819-cplusplus-parser-simplified-lexical-analyzer.html#post968919
*/

#include <iostream>
#include <string>

#include "keywords.h"
#include "search.h"
#include "tokens.h"

/* Homework
int _while_counter = 0;
int _if_counter = 0;
int _equal_counter = 0;
int _identifiers_counter = 0; */

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