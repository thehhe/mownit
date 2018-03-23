#include "Lab2.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>
#include <stdio.h>


using namespace std;

int main() {

    Set_of_equations_Double s;


    for(int i=0; i<N; ++i) {
        for(int j=0; j<N; ++j) {
            if(i == j) s.A[i][j] = 5;
            else if((i-j) < 0) s.A[i][j] = 1/(((double)j+1)-((double)i+1)+5);
            else if((i-j) > 0) s.A[i][j] = 1/(((double)i+1)-((double)j+1)+5);
        }
    }

    //s.print_matrix(s.A);
    double x[N];
    srand(time(NULL));
    double tmp[N] = {0};
    for(int a=0; a<3; ++a) {

        for (int i = 0; i < N; ++i) {
            int element = rand() % 2;
            if (element == 1) x[i] = 1.0;
            else x[i] = -1.0;
        }
        std::cout << "MACIERZ " << N << "x" << N << "\n\n";
        double omega = 1.99;
        std::cout << "OMEGA: " << omega << "\n";
        std::cout << "X:\n";
        s.print_vector(x);
        std::cout << "\n";

        double *b = s.m.matrix_x_vector(s.A, x);
        for (int i = 0; i < N; ++i)s.b[i] = b[i];
        cout << "\n";
//    s.print_vector(s.b);


//    for(int i=0; i<s.N; ++i) {
//        for (int j = 0; j < s.N; ++j) s.A[i][j] = arr[i][j];
//    }
//    double b[] = {30, 0, -10, 5};
//    for(int i=0; i<s.N; ++i) s.b[i] = b[i];
//

        double ros[] = {1e-2, 1e-5, 1e-10};
        for (int i = 0; i < 3; ++i) {
            std::cout << "\nro = " << ros[i] << " \n\n";

            for (int i = 0; i < N; ++i) s.newx[i] = tmp[i];
            for (int i = 0; i < N; ++i) s.x[i] = 0;

            std::cout << "WEKTOR STARTOWY: \n";
            s.print_vector(s.newx);

            s.solve_set(ros[i], 2);

            //s.solve_SOR(ros[i], 2, omega);
            std::cout << "WYNIK: \n";
            s.print_vector(s.newx);
            //s.print_vector(s.newxSOR);

        }

        for (int j = 0; j < N; ++j) tmp[j] = 100 * ((a + 1) * (a + 1) * (a + 1) * (a + 1) * (a + 1) *
                                                    (a + 1));//(((200*j)^(1423*j))+54*j)%(150*(j+a+20));
        std::cout << "--------------------------------------------------------------\n";
    }


    double radius = s.find_spectral_radius();
    cout<<"\n"<<radius<<"\n";

    return 0;
}