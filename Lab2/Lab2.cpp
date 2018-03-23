//
// Created by Maciek on 2016-03-16.
//

#include <iostream>
#include <math.h>
#include "Lab2.h"


double* Matrix_Operations_Double::matrix_x_vector (double matrix[][N], double vector[]) {
//        float tmp=0;
    double result[N];
    for(int i=0; i<N; ++i) {
        result[i] = 0;
        for(int j=0; j<N; ++j) {
            double m = (matrix[i][j]*vector[j]);
            result[i] = result[i] + m;
        }
    }
    return result;
}

void Matrix_Operations_Double::swap_column(double matrix[][N], int column1, int column2) {
    double tmp;
    for(int i=0; i<N; ++i) {
        tmp = matrix[i][column1];
        matrix[i][column1] = matrix[i][column2];
        matrix[i][column2] = tmp;
    }
}

void Matrix_Operations_Double::swap_row(double matrix[][N], int row1, int row2){
    double tmp;
    if(row1 == row2) return;
    for(int i=0; i<N; ++i) {
        tmp = matrix[row1][i];
        matrix[row1][i] = matrix[row2][i];
        matrix[row2][i] = tmp;
    }
}

void Matrix_Operations_Double::multiply_row(double matrix[][N], double mul, int row){
    for(int i=0; i<N; ++i) {
        matrix[row][i] *= mul;
    }
}

void Matrix_Operations_Double::sub_rows(double matrix[][N], int row1, int row2) {
    if(row1 == row2) return;
    for(int i=0; i<N; ++i) {
        matrix[row1][i] -= matrix[row2][i];
    }
}

void Set_of_equations_Double::print_matrix(double A[][N]) {
    for(int i=0; i<N; ++i) {
        for(int j=0; j<N; ++j)
            std::cout<<A[i][j]<<" ";
        std::cout<<"\n";
    }
}
void Set_of_equations_Double::print_vector(double x[]) {
    for(int i=0; i<N; i++) std::cout<<x[i]<<"\n";
}

void Set_of_equations_Double::no0_diagonal(double A[][N]) {
    Matrix_Operations_Double m;
    for(int i=0; i<N; ++i) {
        if(A[i][i] == 0) {
            for(int j=0; j<N; ++j) {
                if(A[j][i] != 0 && A[i][j] != 0) m.swap_row(A, i, j);
            }
        }
    }
}

void Set_of_equations_Double::make_LDU() {

    for(int i=0; i<N; ++i) {
        for(int j=0; j<N; ++j) {
            if(j != i){
                LU[i][j] = A[i][j];
                D[i][j] = 0;
                P[i][j] = 0;
            } else {
                D[i][i] = A[i][i];
                P[i][i] = A[i][i];
                LU[i][j] = 0;
            }
        }
    }
}

void Set_of_equations_Double::mD_times_LU() { //-(D^-1) * LU = Q
    for(int i=0; i<N; ++i) {
        P[i][i] = -1/D[i][i];
        D[i][i] = 1/D[i][i];
    } //P = -D^-1

    double tmp_vect[N];
    for(int i=0; i<N; ++i) {
        for(int j=0; j<N; ++j) {
            tmp_vect[j] = LU[j][i];
        }
        double* res = m.matrix_x_vector(P, tmp_vect);
        for(int j=0; j<N; ++j) Q[j][i] = res[j];
    }
//    for(int i=0; i<N; ++i) {
//        for(int j=0; j<N; ++j) std::cout<<Q[i][j]<<" ";
//        std::cout<<"\n";
//    }
}

void Set_of_equations_Double::inc_approx() {

    Matrix_Operations_Double m2;
    double* Qx = m2.matrix_x_vector(Q, x);
    for(int i=0; i<N; ++i) {
        double tmp = newx[i];
        x[i] = tmp;
        newx[i] =  Qx[i];
    }

    double* Db = m.matrix_x_vector(D, b);
    for(int i=0; i<N; ++i) {
        newx[i] += Db[i];
    }

}


double* Set_of_equations_Double::solve_set(double ro, int mode) { //mode 1 -> ||x(i+1)-x(i)|| mode 2 -> ||Ax(i)-b||
    no0_diagonal(A);
    make_LDU();
    mD_times_LU();
    for(int i=0; i<N; ++i) x[i]=0.0;
    double normv[N] ={0};
    double norm = 0;
    //for(int i=0; i<N; ++i) newx[i] = 100;
    int i=0;
    if(mode == 1) {
        do {
            inc_approx();
            for(int i=0; i<N; ++i) normv[i] = newx[i] - x[i];
            norm = count_norm(normv);
            ++i;
        } while (norm > ro);
    } else {
        do {
            inc_approx();
            for(int i=0; i<N; ++i) normv[i] = m.matrix_x_vector(A, x)[i] - b[i];
            norm = count_norm(normv);
            ++i;
        } while (norm > ro);
    }
    //for(int i=0; i<k; ++i) inc_approx();
    std::cout<<"Liczba iteracji: "<<i<<std::endl;
    return x;
}

double Set_of_equations_Double::count_norm(double *v) {
    double result = 0;
    for(int i=0; i<N; ++i) {
        result += (v[i]*v[i]);
    }
    return sqrt(result);
}


int find_max_Double(double Arr[][N], int p) {
    double result = Arr[p][p];
    int row = p;
    for(int i=p; i<N; ++i) {
        if(result < Arr[i][p]) {
            result = Arr[i][p];
            row = i;
        }
    }
    return row;
}

void Set_of_equations_Double::make_triangular_matrix() {

    Matrix_Operations_Double *m = new Matrix_Operations_Double();
    double mul = 1;
    for(int i=0; i<N; ++i)
        for(int j=0; j<N; ++j)
            goodA[i][j] = A[i][j];
    for (int i = 0; i < N; ++i) {
        int row_with_max = find_max_Double(goodA, i);
        m->swap_row(goodA, i, row_with_max);
        //mul *= (-1); //tutaj akurat nie bedziemy mnozyc, poniewaz macierz ma najwieksze wartosci na przekatnej

        for (int j = i + 1; j < N; ++j) {
            if (goodA[j][i] == 0) continue;
            mul *= (goodA[i][i] / goodA[j][i]);
            m->multiply_row(goodA, (goodA[i][i] / goodA[j][i]), j);

            m->sub_rows(goodA, j, i);
        }
    }
    times = mul;
}

double Set_of_equations_Double::count_determinant() {
    double result = 1;
    for(int i=0; i<N; ++i) {
        result *= goodA[i][i];
    }
    result /= times;
    return result;
}

double count_dot_product(double a[], double b[]) {
    double result = 0;
    for(int i=0; i<N; ++i) result += (a[i]*b[i]);
    return result;
}

void Set_of_equations_Double::make_QRDec() {
    double uk[N], yk[N];
    for(int i=0; i<N; ++i) uk[i] = A[i][0];
    for(int i=0; i<N; ++i) yk[i] = (uk[i]/count_norm(uk));
    for(int i=0; i<N; ++i) QDec[i][0] = yk[i];

    for(int i=1; i<N; ++i) {
        double uvect[N] = {0};
        for(int j=0; j<i; ++j) {
            double tmpQvect[N];
            double tmpAvect[N];
            for(int i=0; i<N; ++i) tmpQvect[i] = QDec[i][j];
            for(int i=0; i<N; ++i) tmpAvect[i] = A[i][j];
            double mul = count_dot_product(tmpQvect, tmpAvect);
            for(int i=0; i<N; ++i) uvect[i] += (tmpQvect[i] * mul);
        }
        double tmpAvect[N];
        for(int j=0; j<N; ++j) tmpAvect[j] = A[j][i];
        for(int j=0; j<N; ++j) QDec[j][i] = tmpAvect[j]-(uvect[j]/count_norm(uvect));
    }

    for(int i=0; i<N; ++i)
        for(int j=0; j<N; ++j) {
            double tmpQvect[N];
            double tmpAvect[N];
            for(int i=0; i<N; ++i) tmpQvect[i] = QDec[i][j];
            for(int i=0; i<N; ++i) tmpAvect[i] = A[i][j];
            if(i <= j) RDec[i][j] = count_dot_product(tmpAvect, tmpQvect);
            else RDec[i][j] = 0;
        }

}

double count_1_norm(double vector[]) {
    double result = 0;
    for(int i=0; i<N; ++i) result += fabs(vector[i]);
    return result;
}
double Set_of_equations_Double::find_spectral_radius() {
    double tmpx[N] = {0};
    double tmpxx[N] = {1};
    double radius=0;
    //Matrix_Operations_Double m;

    for(int i=0; i<10000; ++i) {
        for(int j=0; j<N; ++j) tmpx[j] = tmpxx[j];
        for(int j=0; j<N; ++j) tmpxx[j] = m.matrix_x_vector(Q, tmpx)[j];
        radius = count_norm(tmpxx); //2nd norm
//        radius = count_1_norm(tmpxx);
        for(int j=0; j<N; ++j) tmpxx[j] /= radius;

    }
    return radius;
}

void Set_of_equations_Double::prepare_matrix_for_SOR(double omega) {
    for(int i=0; i<N; ++i)
        for(int j=0; j<N; ++j) {
            if(i == j) QSOR[i][j] = A[i][j]/omega;
            else if(i > j) QSOR[i][j] = A[i][j];
            else QSOR[i][j] = 0;
        }
}

void Set_of_equations_Double::inc_SOR_approx(double omega) {
    for(int i=0; i<N; ++i) xSOR[i] = newxSOR[i];
    for(int i=0; i<N; ++i) {
        double tmp1 = 0;
        for(int j=0; j<i; ++j) tmp1 += A[i][j]*newxSOR[j];
        double tmp2 = 0;
        for(int j=i; j<N; ++j) tmp2 += A[i][j]*xSOR[j];

        newxSOR[i] = xSOR[i] + (omega/A[i][i])*(b[i]-tmp1-tmp2);
    }
}
double *Set_of_equations_Double::solve_SOR(double ro, int mode, double omega) {
    for(int i=0; i<N; ++i) x[i]=0.0;
    double normv[N] ={0};
    double norm = 0;
    //for(int i=0; i<N; ++i) newxSOR[i] = 100;
    int i=0;
    if(mode == 1) {
        do {
            inc_SOR_approx(omega);
            for(int i=0; i<N; ++i) normv[i] = newxSOR[i] - xSOR[i];
            norm = count_norm(normv);
            ++i;
        } while (norm > ro);
    } else {
        do {
            inc_SOR_approx(omega);
            for(int i=0; i<N; ++i) normv[i] = m.matrix_x_vector(A, xSOR)[i] - b[i];
            norm = count_norm(normv);
            ++i;
        } while (norm > ro);
    }
    //for(int i=0; i<k; ++i) inc_approx();
    std::cout<<"Liczba iteracji: "<<i<<std::endl;
    return x;
}
