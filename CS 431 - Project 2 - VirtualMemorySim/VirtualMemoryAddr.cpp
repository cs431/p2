#include "VirtualMemoryAddr.h"

VirtualMemoryAddr::VirtualMemoryAddr(char wtm, string vma)
{
	setWTM(wtm);
	setVMAddr(vma);
}

VirtualMemoryAddr::VirtualMemoryAddr(char wtm, string vma, double d = NULL)
{
	setWTM(wtm);
	setVMAddr(vma);
	setDec(d);
}

bool VirtualMemoryAddr::getWTM()
{
	return writeToMem;
}

void VirtualMemoryAddr::setWTM(char wtm	)
{
	if (wtm = '1') 
	{
		writeToMem = true;
	}
	else 
	{
		writeToMem = false;
	}
}

string VirtualMemoryAddr::getVMAddr()
{
	return vmAddr;
}

void VirtualMemoryAddr::setVMAddr(string vma)
{
	vmAddr = vma;
}

double VirtualMemoryAddr::getDec()
{
	return dec;
}

void VirtualMemoryAddr::setDec(double d)
{
	dec = d;
}

VirtualMemoryAddr::~VirtualMemoryAddr()
{
}
