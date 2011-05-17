/*
    Orignal code
    http://cboard.cprogramming.com/cplusplus-programming/129819-cplusplus-parser-simplified-lexical-analyzer.html#post968919
*/

#include <iostream>
#include <string>

#include "keywords.h"
#include "search.h"
#include "tokens.h"

int main() {
    std::string line;
    
    while(std::getline(std::cin, line)) {
        tokens(line); // print tokens
    }
    
    return 0;
}