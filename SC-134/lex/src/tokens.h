#include <string>
#include <cctype>

void tokens(const std::string &data);
void tokens(const std::string &data) {
    enum {
        MODE_OUTSIDE,
        MODE_IN_STRING,
        MODE_IN_VAR,
        MODE_IN_WORD,
        MODE_SYMBOL
    } mode = MODE_OUTSIDE;
    
    int is_string = 0;
    std::string string;
    std::string var;
    std::string word;
    std::string::size_type x = 0;
    
    while(x < data.length()) { // read all the characters in the line
        char c = data[x]; // curent char
        
        switch(mode) {
            case MODE_OUTSIDE:
                if (std::isspace(c) || std::iscntrl(c)) { // ignore spaces
                    x++; // go to the next char
                    
                } else if (c == '\"') { // if " is detected
                    mode = MODE_IN_STRING; // it is a string
                    x++; // go to the next char
                    
                } else if (c == '@') { // if @ is detected
                    mode = MODE_IN_VAR; // it is a variable
                    x++; // go to the next char
                            
                } else if (std::isalnum(c)) { // else it may be a word or number
                    mode = MODE_IN_WORD;
                
                } else { // else it is a symbol (operator)
                    mode = MODE_SYMBOL;
                }
                break;
                
            case MODE_IN_STRING:
                if (c == '\"') { // if we find another " the string is donde
                    std::cout << "string: " << string << std::endl;
                    string.erase();
                    mode = MODE_OUTSIDE;
                    
                } else { // store all the chars in the string
                    string += c;
                }
                x++; // next char
                break;
            
            case MODE_IN_VAR:
                if (std::isalnum(c) || c == '@') { // the cariables can only contain letters and numbers
                    var += c; // store all the chars
                    x++; // next char
                    
                } else { // the variable name is done
                    std::cout << "variable: @" << var << std::endl;
                    var.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_IN_WORD:
                if (std::isalnum(c)) { // it's an unknown word
                    word += c; // store all the chars
                    x++; // next char
                    
                } else {
                    if (search_string(keywords, keywords_size, word)) { // if the word is in the reserved words list
                        std::cout << "reserved: " << word << std::endl;
                        /* Homework
                        if (word.compare("while") == 0) {
                            _while_counter++;
                        } else if (word.compare("if") == 0) {
                            _if_counter++;
                        }*/
                        
                    } else {
                        int number = atoi(word.c_str()); // check if it is an integer
                        
                        if (number) { // if it is an integer
                            std::cout << "digit: " << number << std::endl;
                            
                        } else { // it is an unknown word
                            std::cout << "word: " << word << std::endl;
                            /* Homework
                            _identifiers_counter++; */
                        }
                    }
                    word.erase();
                    mode = MODE_OUTSIDE;
                }
                break;
                
            case MODE_SYMBOL:
                if (!std::isalnum(c) && !std::isspace(c)) { // if it is not a char or space
                    if (c == ';') { // it's the end of the line
                        std::cout << "end of line" << c << std::endl;
                        
                    } else { // it is a symbol
                        std::cout << "[" << c << "] " << std::endl;
                        /* Homework
                        if (c == '=') {
                            _equal_counter++;
                        } */
                    }
                    x++;
                    
                } else {
                    mode = MODE_OUTSIDE;
                }
                break;
        }
    }
}