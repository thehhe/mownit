cmake_minimum_required(VERSION 3.3)
project(MownitLab2)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Lab2.cpp Lab2.h)
add_executable(MownitLab2 ${SOURCE_FILES})