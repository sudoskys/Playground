import fs from 'fs';
var xml = "<root>Hello xml2js!</root>"

import { XMLParser, XMLBuilder, XMLValidator} from "fast-xml-parser"

const parser = new XMLParser();
let jObj = parser.parse(xml);
console.log(jObj);