cmake_minimum_required(VERSION 3.5)

project(HelloZed LANGUAGES CXX)

#set(CMAKE_CXX_STANDARD 17)
#set(CMAKE_CXX_STANDARD_REQUIRED ON)
add_executable(HelloZed main.cpp)

install(TARGETS HelloZed RUNTIME DESTINATION bin)
