/* Copyright (c) 2009-2017. Authors: see NOTICE file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// THIS IS THE COMMON EXTERNAL CYTOMINE IRIS CONFIGURATION FILE
// use ConfigSlurper Syntax to configure the settings
import org.apache.log4j.DailyRollingFileAppender

println "loading common configuration..."

// ######################
// Cytomine-Core settings
// ######################
grails.cytomine = [
        // the image server, where IRIS gets thumbnails and tiles
        // add a placeholder {serverID} to enable sharding for
        // parallel image tile fetching from random server ids (0-10)
        // if there is no {serverID} present, all requests are sent to one server
        image: [
                host : "http://$IMS_URL1"
        ],
        // the Cytomine-Core host (where the projects are managed)
        host : "http://$CORE_URL",
        // just the web-address of the Cytomine project (can be left unchanged)
        web  : "http://www.cytomine.be",
        // IRIS specific configuration, don't modify the structure
        apps : [
                iris: [
                        server     : [
                                admin: [
                                        // CHANGE THIS INFORMATION TO YOUR ADMIN'S INFORMATION
                                        name        : "$IRIS_ADMIN_NAME",
                                        organization: "$IRIS_ADMIN_ORGANIZATION_NAME",
                                        email       : "$IRIS_ADMIN_EMAIL"
                                ]
                        ],
                        // configure a demo project for this IRIS instance which will always be enabled to its users
                        // if none is specified, all projects will be disabled by default
                        demoProject: [
                                cmID: 0
                        ],
                        // an empty synchronization object, we require in environment specific config files
                        sync       : [:]
                ]
        ]
]

// ###################
// backend/db settings
// ###################
grails.logging.jul.usebridge = true
// disable the console if you don't want to have access to the database,
// otherwise the db console is available (user sa, password: <none>) at the
// url defined by 'grails.dbconsole.urlRoot'
grails.dbconsole.enabled = true
// do not change this URL unless you know what you are doing ;-)
grails.dbconsole.urlRoot = '/admin/dbconsole'

// ###################
// log4j configuration
// ###################
// default logging goes into the Tomcat log directory
def logRootDir = System.properties.getProperty('catalina.base')
// you can also define e.g. the $USER_HOME as log directory
//def logRootDir = System.properties.getProperty('user.home')
if (!logRootDir) logRootDir = '.'
def logDirectory = (logRootDir + "/logs")


// available log levels for log4j:
// ALL > TRACE > DEBUG > INFO > WARN > ERROR > FATAL > OFF
log4j = {
    appenders {
        console     name: 'stdout', layout: pattern(conversionPattern: '%d [%t] %-5p %c{2} - %m%n')
        appender    new DailyRollingFileAppender(
                        name: 'dailyFileAppender',
                        datePattern: "'.'yyyy-MM-dd",  // See the log4j API for all patterns.
                        fileName: (logDirectory + "/iris/iris.log"),
                        layout: pattern(conversionPattern: '%d [%t] %-5p %c{2} %x - %m%n')
        )

        rollingFile     name: "stacktrace", maxFileSize: 4096,
                        file: (logDirectory + "/iris/iris-stacktrace.log")
    }

    root {
        info    'stdout', 'dailyFileAppender'
    }

    // common logging
    error   'org.codehaus.groovy.grails.web.servlet',        // controllers
            'org.codehaus.groovy.grails.web.pages',          // GSP
            'org.codehaus.groovy.grails.web.sitemesh',       // layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping',        // URL mapping
            'org.codehaus.groovy.grails.commons',            // core / classloading
            'org.codehaus.groovy.grails.plugins',            // plugins
            'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'

//	trace 'org.hibernate.type.descriptor.sql.BasicBinder'   // verbose SQL information

    // cytomine java client
    warn 'be.cytomine.client'

    environments {
        development {
            debug   'grails.app.controllers',
                    'grails.app.services',
                    'be.cytomine.apps.iris'
                    // 'org.hibernate.SQL',
                    'grails.assets'

            debug 'grails.app.jobs'
        }
        production {
            // let the application run in debug log mode for all
            // information
            debug   'grails.app.controllers',
                    'grails.app.services',
                    'be.cytomine.apps.iris',
                    'grails.app.jobs'
        }
    }
}

println 'loaded common configuration.'