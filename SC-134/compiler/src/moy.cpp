
#include <iostream>
#include <string>
#include <vector>

std::vector< std::vector<int> > tokens;
std::vector<int> token;

#include "tokens.h"
#include "search.h"
#include "lexical.h"
#include "parser.h"

int main() {
    std::string line;
    
    while(std::getline(std::cin, line)) { // read line by line of the given file
        token.clear();
        lex(line); // get tokens
        tokens.push_back(token);
    }
    parse(tokens); // parse tokens
    
    return 0;
}