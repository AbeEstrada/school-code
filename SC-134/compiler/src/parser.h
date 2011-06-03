#include <vector>

void check_grammar(int current, int next, int rules[], int size) {
    int check = search_int(rules, size, next); // search if the next token is in the rules
    if (check < 0) { // if the next token isn't in the rules
        std::cout << "<< ERROR NEXT TO " << std::endl; // show error
        exit(1); // stop
    }
}

void parse(std::vector< std::vector<int> > &v_tokens) {
    for (int i = 0; i < v_tokens.size(); i++) { // go to each line of the vector
        if (v_tokens[i].size() > 0) {
            std::cout << "Line " << i+1 << ": ";
        }
        for (int j = 0; j < v_tokens[i].size(); j++) { // go to each column of the vector
            int current = v_tokens[i][j]; // curent token
            int next = v_tokens[i][j+1]; // next token
            // get current token and set rules and check grammar
            switch(current) {
                case D_STRING: {
                    std::cout << "STRING ";
                    if (j != 0) { // if it is not in the beggining
                        int rules[] = {
                            D_DELIMITER
                        };
                        int rules_size = sizeof(rules) / sizeof(int);
                        check_grammar(current, next, rules, rules_size);
                    } else {
                        std::cout << "<< ERROR BEGGINING OF THE LINE" << std::endl;
                        exit(1);
                    }
                    break;
                }
                
                case D_VARIABLE: {
                    std::cout << "VARIABLE ";
                    if (j == 0) { // if it is the first
                        int rules[] = {
                            D_OPERATOR
                        };
                        int rules_size = sizeof(rules) / sizeof(int);
                        check_grammar(current, next, rules, rules_size);
                        
                    } else {
                        int rules[] = {
                            D_DELIMITER,
                            D_OPERATOR
                        };
                        int rules_size = sizeof(rules) / sizeof(int);
                        check_grammar(current, next, rules, rules_size);
                    }
                    break;
                }
                
                case D_RESERVED: {
                    std::cout << "RESERVED ";
                    int rules[] = {
                        D_DELIMITER,
                        D_STRING,
                        D_VARIABLE,
                        D_DIGIT
                    };
                    int rules_size = sizeof(rules) / sizeof(int);
                    check_grammar(current, next, rules, rules_size);
                    break;
                }
                
                case D_DIGIT: {
                    std::cout << "DIGIT ";
                    if (j != 0) { // if it is not in the beggining
                        int rules[] = {
                            D_DELIMITER,
                            D_OPERATOR
                        };
                        int rules_size = sizeof(rules) / sizeof(int);
                        check_grammar(current, next, rules, rules_size);
                    } else {
                        std::cout << "<< ERROR BEGGINING OF THE LINE" << std::endl;
                        exit(1);
                    }
                    break;
                }
                
                case D_WORD: {
                    std::cout << "WORD << NOT DEFINED" << std::endl;
                    exit(1);
                    break;
                }
                
                case D_DELIMITER: {
                    std::cout << "DELIMITER ";
                    if ((j == 0) || (next == D_DELIMITER)) { // if it is in the beggining
                        std::cout << "<< ERROR UNEXPECTED DELIMITER" << std::endl;
                        exit(1);
                    }
                    break;
                }
                
                case D_OPERATOR: {
                    std::cout << "OPERATOR ";
                    if (j != 0) { // if it is not in the beggining
                        int rules[] = {
                            D_DELIMITER,
                            D_DIGIT,
                            D_STRING,
                            D_VARIABLE
                        };
                        int rules_size = sizeof(rules) / sizeof(int);
                        check_grammar(current, next, rules, rules_size);
                    } else {
                        std::cout << "<< ERROR BEGGINING OF THE LINE" << std::endl;
                        exit(1);
                    }
                    break;
                }
                
                default:
                    break;
            }
        }
        if (v_tokens[i].size() > 0) {
            std::cout << std::endl;
        }
    }
}