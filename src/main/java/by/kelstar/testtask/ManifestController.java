/*
 * Copyright IBM Corp. 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package by.kelstar.testtask;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ManifestController {

  @RequestMapping(value="/generate", method=RequestMethod.POST)
  public String generator(@RequestBody Manifest manifest, HttpServletResponse  response) {
    return manifest.toString();
  }
  
  @RequestMapping(value="/download", method=RequestMethod.POST)
  public void download(HttpServletRequest request, HttpServletResponse  response) throws IOException {
    Manifest manifest = new Manifest(request);
    response.setHeader( "Content-Disposition", "attachment; filename=manifest.yml");
    response.setContentType("text/plain");
    response.getWriter().write(manifest.toString());
  }
  
}

