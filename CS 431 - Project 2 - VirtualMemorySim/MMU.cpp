#include "MMU.h"



void MMU::processVMAddr(VirtualMemoryAddr vma)
{
	if (vma.getWTM()) 
	{
		// write to memory
	}
	else 
	{
		// output to file
	}
}

MMU::MMU()
{
}


MMU::~MMU()
{
}
