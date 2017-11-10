#pragma once

//DataSize.h
//Discription: This class is to hold our constants for memory sizes, I felt like it would be useful.

#include <iostream>
using namespace std;

class DataSize
{
public:
	DataSize(int,int,int,int);
	void setCPUWidth(int);
	int getcpuWidth();
	void setPhyscicalMemory(int);
	int getPhyscicalMemory();
	void setPageOffset(int);
	int getPageOffset();
	void setTlbSize(int);
	int getTlbSize();
	
	void print();

	~DataSize();
private:
	int cpuWidth;
	int physcicalMemory;
	int pageOffset;
	int tlbSize;
};

