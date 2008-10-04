/*
    $Id$

    See readme.txt in this directory for how to unit test SqlTool.

    Tests:  --sql arg

    HARNESS_METADATA        BEGIN         
    arg                 --noAutoFile
    arg                 --sql
    arg                 \p See this
    requireStdoutRegex   See this
    arg                 mem 
    HARNESS_METADATA        END       
*/

\p See this
