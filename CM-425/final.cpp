#include <iostream>
#include <string>

using namespace std;

// Prototipos de las funciones
void choices();
void greeting();
long fact(long);

int main () {
    choices(); // Mostrar menú
    return 0;
}

void greeting() { // Opción (1)
    string name;
    
    cout << "Introduce tu nombre: ";
    cin >> name;
    
    cout << "\nHola " << name << "\n";
}

long fact(long n) { // Opción (2)
    if (n > 1)
        return (n * fact(n-1));
    else
        return (1);
}

void avg() { // Opción 3
    float number = 0;
    float total = 0;
    int i = 0;
    do {
        cout << "Introduce una cantidad (0 para finalizar): ";
        cin >> number;
        total = total + number;
        i++;
    } while (number != 0);
    cout << "Promedio: " << (total/(i-1)) << "\n";
}

void choices() {
    int choice;
    
    cout << "\n";
    cout << "=== Inicio ====================\n";
    cout << "(1) Saludar\n";
    cout << "(2) Factorial\n";
    cout << "(3) Promedio\n\n";
    cout << "Selecciona una opción, (0) para salir: ";
    cin >> choice;
    
    switch(choice) {
        case 0: // Salir
            exit(0);
            break;
            
        case 1: // Saludo
            greeting();
            break;
            
        case 2: // Factorial
            long factorial;
            cout << "Introduce un número: ";
            cin >> factorial;
            cout << factorial << "! = " << fact(factorial) << "\n";
            break;
            
        case 3:
        avg();
            break;
    }
    choices();
}