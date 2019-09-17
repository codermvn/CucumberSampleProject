package com.lh.test.stepDefinition;

import com.lh.test.OBR.Helper;
import com.lh.test.OBR.OBRService;
import com.lh.test.pages.FrameImportConfirmationPage;
import com.lh.test.pages.ExportDailogPage;
import com.lh.test.pages.FrameDeleteDialogPage;
import com.lh.test.pages.FrameEditPage;
import com.lh.test.pages.FrameListPage;
import com.lh.test.pages.FrameOutOfFaresPage;
import com.lh.test.pages.Loginpage;
import com.lh.test.pages.OutlookPortal;
import com.lh.test.pages.PLPHomepage;
import com.lh.test.pages.PLPOBRDetailspage;
import com.lh.test.pages.PLPOBRListpage;

public class BaseSteps {
	Loginpage login = new Loginpage();
	PLPHomepage homePage = new PLPHomepage();
	PLPOBRListpage oBRListpage = new PLPOBRListpage();
	PLPOBRDetailspage oBRDetailsPage = new PLPOBRDetailspage();
	OBRService obrService = new OBRService();
	Helper helper = new Helper();
	FrameListPage frameListPage = new FrameListPage();
	FrameImportConfirmationPage confirmPage= new FrameImportConfirmationPage();
	FrameDeleteDialogPage deletePage = new FrameDeleteDialogPage();
	 ExportDailogPage exportDailogPage= new ExportDailogPage();
	 OutlookPortal outlookPortal = new OutlookPortal();
    public static int sleep = 2000;
	FrameEditPage frameEditPage = new FrameEditPage();
	 FrameOutOfFaresPage framesOutOfFaresPage = new FrameOutOfFaresPage();
	
}
