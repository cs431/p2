#pragma once
#include "VM_Entry.h"
class TBL_Entry : public VM_Entry
{
public:
	int v_pageNum;
	TBL_Entry();
	~TBL_Entry();
};

