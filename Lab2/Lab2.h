//
// Created by Maciek on 2016-03-16.
//

#ifndef MOWNITLAB2_LAB2_H
#define MOWNITLAB2_LAB2_H
#define N 20

#endif //MOWNITLAB2_LAB2_H

class Matrix_Operations_Double {
public:
    //static const int N = 20;
    double *matrix_x_vector(double matrix[][N], double vector[]);
    void swap_column(double matrix[][N], int column1, int column2);
    void swap_row(double matrix[][N], int row1, int row2);
    void multiply_row(double matrix[][N], double mul, int row);
    void sub_rows(double matrix[][N], int row1, int row2);
};

class Set_of_equations_Double {
public:
    //static const int N = 20;
    double A[N][N];

    double LU[N][N];
    double iteration_matrix[N][N];
//    double U[N][N];
    double D[N][N];
    double P[N][N];
    double Q[N][N];
    double x[N] = {0};
    double newx[N] = {0};
    double b[N];

    double goodA[N][N];
    double times;

    Matrix_Operations_Double m;
    //double *solve_set();
    void print_matrix(double A[][N]);
    void print_vector(double x[]);

    void no0_diagonal(double A[][N]);
    void make_LDU();
    void mD_times_LU();
    void inc_approx();
    double* solve_set(double ro, int mode);
    double count_norm(double v[]);
    //nvm
    void make_triangular_matrix();
    double count_determinant();

    double QDec[N][N];
    double RDec[N][N];

    void make_QRDec();
    double find_spectral_radius();

    //SOR
    double QSOR[N][N];
    double xSOR[N] = {0};
    double newxSOR[N] = {0};
    double omega;
    void prepare_matrix_for_SOR(double omega);
    void inc_SOR_approx(double omega);
    double* solve_SOR(double ro, int mode, double omega);
};
