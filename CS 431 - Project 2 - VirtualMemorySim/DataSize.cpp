#include "DataSize.h"
#include "DataSize.h"



DataSize::DataSize(int cw, int pm, int po, int ts)
{
	cpuWidth = cw;
	physcicalMemory = pm;
	pageOffset = po;
	tlbSize = ts;
}

DataSize::~DataSize()
{
}


void DataSize::setCPUWidth(int cw)
{
	cpuWidth = cw;
}

int DataSize::getcpuWidth()
{
	return cpuWidth;
}

void DataSize::setPhyscicalMemory(int pm)
{
	physcicalMemory = pm;
}

int DataSize::getPhyscicalMemory()
{
	return physcicalMemory;
}

void DataSize::setPageOffset(int po)
{
	pageOffset = po;
}

int DataSize::getPageOffset()
{
	return pageOffset;
}

void DataSize::setTlbSize(int ts)
{
	tlbSize = ts;
}

int DataSize::getTlbSize()
{
	return tlbSize;
}

void DataSize::print()
{
	cout << "CPU Width: " << cpuWidth << "bits | Physical Memory Width: " << physcicalMemory << "bits | Page Offset: " << pageOffset << "bits | TLB Size: " << tlbSize << "bits |" << endl;
}
