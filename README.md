# WAIP PROJECT

Inside simulator:
* Description (can be any, I set it to this): `WAIP SmartHouse\tUL, Messaging (SMS)`
* Command (may require changing the path to simulator (R5A02 and above)): `java -classpath "cls;C:\Program Files (x86)\Ericsson\Network Resource Gateway SDK\R5A02\examples/tools/cls;;C:\Program Files (x86)\Ericsson\Network Resource Gateway SDK\R5A02/lib/corba/sun/lib/corba_sun.jar;C:\Program Files (x86)\Ericsson\Network Resource Gateway SDK\R5A02/lib/coreapi/lib/coreapi.jar;C:\Program Files (x86)\Ericsson\Network Resource Gateway SDK\R5A02/lib/utilityapi/lib/utilityapi.jar;" pg.waip.smarthouse.Main`
* Directory: `D:\path\to\your\project\cls`

Command tested on just Configuration NestedProperties, may require further adjustments.

## Build
Build only with the `build.bat`, it may or may not require you to change path to simulator in `sim_path` variable. 

**Please don't commit changed build.bat**

## Libraries
Libraries linked to compiler/IDE:
* corba_sun: `...\sim_path\lib\corba\sun\lib\corba_sun.jar`
* coreapi: `...\sim_path\lib\coreapi\lib\coreapi.jar`
* utilityapi: `...\sim_path\lib\utilityapi\lib\utilityapi.jar`
* example/tools: `...\sim_path\examples\tools\cls`
* examples/att: `...\sim_path\examples\att\cls`