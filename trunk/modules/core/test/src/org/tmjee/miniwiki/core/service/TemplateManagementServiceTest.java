package org.tmjee.miniwiki.core.service;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.JpaCallback;
import org.tmjee.miniwiki.core.domain.*;
import org.tmjee.miniwiki.client.server.PagingInfo;
import org.tmjee.miniwiki.client.domain.UiWikis;
import org.tmjee.miniwiki.client.domain.UiWiki;
import org.tmjee.miniwiki.utils.UiWikiNameComparator;
import org.hsqldb.lib.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * @author tmjee
 * @version $Date$ $Id$
 */
public class TemplateManagementServiceTest extends AbstractDbTestCase {

    public TemplateManagementServiceTest(String name) throws IOException {
        super(name);
    }

    protected void postSetUp() throws Exception {
        getTestingSupportService().doService(new TestingSupportService.TestingAction() {
            public void action(JpaTemplate template) throws Exception {
                template.execute(new JpaCallback() {
                    public Object doInJpa(EntityManager entityManager) throws PersistenceException {

                        // ====================
                        // === setup users
                        // ====================
                        final User toby = new User();
                        toby.setUsername("Toby");
                        toby.setPassword("Toby_Password");
                        toby.setFirstName("Toby_Firstname");
                        toby.setLastName("Toby_Lastname");
                        toby.setEnabled(true);
                        toby.setDescription("Toby_Description");

                        final User jim = new User();
                        jim.setUsername("Jim");
                        jim.setPassword("Jim_Password");
                        jim.setFirstName("Jim_Firstname");
                        jim.setLastName("Jim_Lastname");
                        jim.setEnabled(true);
                        jim.setDescription("Jim_Description");

                        final User mary = new User();
                        mary.setUsername("Mary");
                        mary.setPassword("Mary_Password");
                        mary.setFirstName("Mary_Firstname");
                        mary.setLastName("Mary_Lastname");
                        mary.setEnabled(true);
                        mary.setDescription("Mary_Description");



                        // =====================
                        // ==== setup Wiki 1
                        // =====================
                        Wiki wiki1 = new Wiki();
                        wiki1.setName("Wiki1");
                        wiki1.setPriviledges(new HashSet<WikiPriviledge>() {
                            {
                                WikiPriviledge priviledge1 = new WikiPriviledge();
                                priviledge1.setName("Priviledge1");
                                priviledge1.setValues(new HashSet<WikiPriviledgeValue>() {
                                    {
                                        WikiPriviledgeValue val1 = new WikiPriviledgeValue();
                                        val1.setValue("Priviledge1V1");
                                        add(val1);
                                        WikiPriviledgeValue val2 = new WikiPriviledgeValue();
                                        val2.setValue("Priviledge1V2");
                                        add(val2);
                                    }
                                });
                                add(priviledge1);
                                
                                WikiPriviledge priviledge2 = new WikiPriviledge();
                                priviledge2.setName("Priviledge2");
                                priviledge2.setValues(new HashSet<WikiPriviledgeValue>() {
                                    {
                                        WikiPriviledgeValue val1 = new WikiPriviledgeValue();
                                        val1.setValue("Priviledge2V1");
                                        add(val1);
                                        WikiPriviledgeValue val2 = new WikiPriviledgeValue();
                                        val2.setValue("Priviledge2V2");
                                        add(val2);
                                    }
                                });
                                add(priviledge2);
                            }
                        });
                        wiki1.setProperties(new HashSet<WikiProperty>() {
                            {
                                WikiProperty prop1 = new WikiProperty();
                                prop1.setName("Prop1");
                                prop1.setValue("Val1");
                                add(prop1);

                                WikiProperty prop2 = new WikiProperty();
                                prop2.setName("Prop2");
                                prop2.setValue("Val2");
                                add(prop2);
                            }
                        });
                        wiki1.setSpaces(new HashSet<Space>() {
                            {
                                final Space space1 = new Space();
                                space1.setName("Space1");
                                space1.setCreator(toby);
                                space1.setPriviledges(new HashSet<SpacePriviledge>() {
                                    {
                                        SpacePriviledge priv1 = new SpacePriviledge();
                                        priv1.setName("Priv1");
                                        priv1.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv1);

                                        SpacePriviledge priv2 = new SpacePriviledge();
                                        priv2.setName("Priv2");
                                        priv2.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv2);
                                    }
                                });
                                space1.setProperties(new HashSet<SpaceProperty>() {
                                    {
                                        SpaceProperty prop1 = new SpaceProperty();
                                        prop1.setName("Prop1");
                                        prop1.setValue("Prop1Value");
                                        add(prop1);

                                        SpaceProperty prop2 = new SpaceProperty();
                                        prop2.setName("Prop2");
                                        prop2.setValue("Prop2Value");
                                        add(prop2);
                                    }
                                });
                                space1.setPages(new HashSet<Page>() {
                                    {
                                        final Page page1 = new Page();
                                        page1.setName("S1_Page1");
                                        page1.setLastModifiedUser(toby);
                                        page1.setCreator(toby);
                                        page1.setSpace(space1);
                                        page1.setParent(null);
                                        page1.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page1.setChildren(new HashSet<Page>() {
                                            {
                                                final Page child1_1 = new Page();
                                                child1_1.setName("S1_Page1_1");
                                                child1_1.setLastModifiedUser(jim);
                                                child1_1.setCreator(jim);
                                                child1_1.setSpace(space1);
                                                child1_1.setParent(page1);
                                                child1_1.setAttachments(null);
                                                child1_1.setPreviousVersions(null);
                                                child1_1.setPriviledges(null);
                                                child1_1.setProperties(null);
                                                child1_1.setChildren(new HashSet<Page>() {
                                                    {
                                                        Page child1_1_1 = new Page();
                                                        child1_1_1.setName("S1_Page1_1_1");
                                                        child1_1_1.setLastModifiedUser(jim);
                                                        child1_1_1.setCreator(jim);
                                                        child1_1_1.setSpace(space1);
                                                        child1_1_1.setParent(child1_1);
                                                        child1_1_1.setAttachments(null);
                                                        child1_1_1.setChildren(null);
                                                        child1_1_1.setPreviousVersions(null);
                                                        child1_1_1.setPriviledges(null);
                                                        child1_1_1.setProperties(null);
                                                        add(child1_1_1);
                                                    }
                                                });
                                                add(child1_1);


                                                final Page child1_2 = new Page();
                                                child1_2.setName("S1_Page1_2");
                                                child1_2.setLastModifiedUser(jim);
                                                child1_2.setCreator(jim);
                                                child1_2.setSpace(space1);
                                                child1_2.setParent(page1);
                                                child1_2.setAttachments(null);
                                                child1_2.setPreviousVersions(null);
                                                child1_2.setPriviledges(null);
                                                child1_2.setProperties(null);
                                                child1_2.setChildren(new HashSet<Page>() {
                                                    {
                                                        Page child1_2_1 = new Page();
                                                        child1_2_1.setName("S1_Page1_2_1");
                                                        child1_2_1.setLastModifiedUser(jim);
                                                        child1_2_1.setCreator(jim);
                                                        child1_2_1.setSpace(space1);
                                                        child1_2_1.setParent(child1_2);
                                                        child1_2_1.setAttachments(null);
                                                        child1_2_1.setChildren(null);
                                                        child1_2_1.setPreviousVersions(null);
                                                        child1_2_1.setPriviledges(null);
                                                        child1_2_1.setProperties(null);
                                                        add(child1_2_1);
                                                    }
                                                });
                                                add(child1_2);
                                            }
                                        });
                                        page1.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page1.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page1.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page1);


                                        final Page page2 = new Page();
                                        page2.setName("S1_Page2");
                                        page2.setLastModifiedUser(toby);
                                        page2.setCreator(toby);
                                        page2.setSpace(space1);
                                        page2.setParent(null);
                                        page2.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page2.setChildren(new HashSet<Page>() {
                                            {
                                                Page child2_1 = new Page();
                                                child2_1.setName("S1_Page2_1");
                                                child2_1.setLastModifiedUser(jim);
                                                child2_1.setCreator(jim);
                                                child2_1.setSpace(space1);
                                                child2_1.setParent(page2);
                                                child2_1.setAttachments(null);
                                                child2_1.setChildren(null);
                                                child2_1.setPreviousVersions(null);
                                                child2_1.setPriviledges(null);
                                                child2_1.setProperties(null);
                                                add(child2_1);
                                            }
                                        });
                                        page2.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page2.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page2.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page2);
                                    }
                                });
                                add(space1);


                                final Space space2 = new Space();
                                space2.setName("Space2");
                                space2.setCreator(jim);
                                space2.setPriviledges(new HashSet<SpacePriviledge>() {
                                    {
                                        SpacePriviledge priv1 = new SpacePriviledge();
                                        priv1.setName("Priv1");
                                        priv1.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv1);

                                        SpacePriviledge priv2 = new SpacePriviledge();
                                        priv2.setName("Priv2");
                                        priv2.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv2);
                                    }
                                });
                                space2.setProperties(new HashSet<SpaceProperty>() {
                                    {
                                        SpaceProperty prop1 = new SpaceProperty();
                                        prop1.setName("Prop1");
                                        prop1.setValue("Prop1Value");
                                        add(prop1);

                                        SpaceProperty prop2 = new SpaceProperty();
                                        prop2.setName("Prop2");
                                        prop2.setValue("Prop2Value");
                                        add(prop2);
                                    }
                                });
                                space2.setPages(new HashSet<Page>() {
                                    {
                                        final Page page1 = new Page();
                                        page1.setName("S2_Page1");
                                        page1.setLastModifiedUser(toby);
                                        page1.setCreator(toby);
                                        page1.setSpace(space2);
                                        page1.setParent(null);
                                        page1.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page1.setChildren(new HashSet<Page>() {
                                            {
                                                Page child1 = new Page();
                                                child1.setName("S2_Page1_1");
                                                child1.setLastModifiedUser(jim);
                                                child1.setCreator(jim);
                                                child1.setSpace(space2);
                                                child1.setParent(page1);
                                                child1.setAttachments(null);
                                                child1.setChildren(null);
                                                child1.setPreviousVersions(null);
                                                child1.setPriviledges(null);
                                                child1.setProperties(null);
                                                add(child1);
                                            }
                                        });
                                        page1.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page1.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page1.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page1);
                                    }
                                });
                                add(space2);
                            }
                        });



                        // ======================
                        // === setup Wiki 2
                        // ======================
                        Wiki wiki2 = new Wiki();
                        wiki2.setName("Wiki2");
                        wiki2.setPriviledges(new HashSet<WikiPriviledge>() {
                            {
                                WikiPriviledge priviledge1 = new WikiPriviledge();
                                priviledge1.setName("Priviledge1");
                                priviledge1.setValues(new HashSet<WikiPriviledgeValue>() {
                                    {
                                        WikiPriviledgeValue val1 = new WikiPriviledgeValue();
                                        val1.setValue("Priviledge1V1");
                                        add(val1);
                                        WikiPriviledgeValue val2 = new WikiPriviledgeValue();
                                        val2.setValue("Priviledge1V2");
                                        add(val2);
                                    }
                                });
                                add(priviledge1);

                                WikiPriviledge priviledge2 = new WikiPriviledge();
                                priviledge2.setName("Priviledge2");
                                priviledge2.setValues(new HashSet<WikiPriviledgeValue>() {
                                    {
                                        WikiPriviledgeValue val1 = new WikiPriviledgeValue();
                                        val1.setValue("Priviledge2V1");
                                        add(val1);
                                        WikiPriviledgeValue val2 = new WikiPriviledgeValue();
                                        val2.setValue("Priviledge2V2");
                                        add(val2);
                                    }
                                });
                                add(priviledge2);
                            }
                        });
                        wiki2.setProperties(new HashSet<WikiProperty>() {
                            {
                                WikiProperty prop1 = new WikiProperty();
                                prop1.setName("Prop1");
                                prop1.setValue("Val1");
                                add(prop1);

                                WikiProperty prop2 = new WikiProperty();
                                prop2.setName("Prop2");
                                prop2.setValue("Val2");
                                add(prop2);
                            }
                        });
                        wiki2.setSpaces(new HashSet<Space>() {
                            {
                                final Space space1 = new Space();
                                space1.setName("Space1");
                                space1.setCreator(toby);
                                space1.setPriviledges(new HashSet<SpacePriviledge>() {
                                    {
                                        SpacePriviledge priv1 = new SpacePriviledge();
                                        priv1.setName("Priv1");
                                        priv1.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv1);

                                        SpacePriviledge priv2 = new SpacePriviledge();
                                        priv2.setName("Priv2");
                                        priv2.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv2);
                                    }
                                });
                                space1.setProperties(new HashSet<SpaceProperty>() {
                                    {
                                        SpaceProperty prop1 = new SpaceProperty();
                                        prop1.setName("Prop1");
                                        prop1.setValue("Prop1Value");
                                        add(prop1);

                                        SpaceProperty prop2 = new SpaceProperty();
                                        prop2.setName("Prop2");
                                        prop2.setValue("Prop2Value");
                                        add(prop2);
                                    }
                                });
                                space1.setPages(new HashSet<Page>() {
                                    {
                                        final Page page1 = new Page();
                                        page1.setName("S1_Page1");
                                        page1.setLastModifiedUser(toby);
                                        page1.setCreator(toby);
                                        page1.setSpace(space1);
                                        page1.setParent(null);
                                        page1.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page1.setChildren(new HashSet<Page>() {
                                            {
                                                final Page child1_1 = new Page();
                                                child1_1.setName("S1_Page1_1");
                                                child1_1.setLastModifiedUser(jim);
                                                child1_1.setCreator(jim);
                                                child1_1.setSpace(space1);
                                                child1_1.setParent(page1);
                                                child1_1.setAttachments(null);
                                                child1_1.setPreviousVersions(null);
                                                child1_1.setPriviledges(null);
                                                child1_1.setProperties(null);
                                                child1_1.setChildren(new HashSet<Page>() {
                                                    {
                                                        Page child1_1_1 = new Page();
                                                        child1_1_1.setName("S1_Page1_1_1");
                                                        child1_1_1.setLastModifiedUser(jim);
                                                        child1_1_1.setCreator(jim);
                                                        child1_1_1.setSpace(space1);
                                                        child1_1_1.setParent(child1_1);
                                                        child1_1_1.setAttachments(null);
                                                        child1_1_1.setChildren(null);
                                                        child1_1_1.setPreviousVersions(null);
                                                        child1_1_1.setPriviledges(null);
                                                        child1_1_1.setProperties(null);
                                                        add(child1_1_1);
                                                    }
                                                });
                                                add(child1_1);


                                                final Page child1_2 = new Page();
                                                child1_2.setName("S1_Page1_2");
                                                child1_2.setLastModifiedUser(jim);
                                                child1_2.setCreator(jim);
                                                child1_2.setSpace(space1);
                                                child1_2.setParent(page1);
                                                child1_2.setAttachments(null);
                                                child1_2.setPreviousVersions(null);
                                                child1_2.setPriviledges(null);
                                                child1_2.setProperties(null);
                                                child1_2.setChildren(new HashSet<Page>() {
                                                    {
                                                        Page child1_2_1 = new Page();
                                                        child1_2_1.setName("S1_Page1_2_1");
                                                        child1_2_1.setLastModifiedUser(jim);
                                                        child1_2_1.setCreator(jim);
                                                        child1_2_1.setSpace(space1);
                                                        child1_2_1.setParent(child1_2);
                                                        child1_2_1.setAttachments(null);
                                                        child1_2_1.setChildren(null);
                                                        child1_2_1.setPreviousVersions(null);
                                                        child1_2_1.setPriviledges(null);
                                                        child1_2_1.setProperties(null);
                                                        add(child1_2_1);
                                                    }
                                                });
                                                add(child1_2);
                                            }
                                        });
                                        page1.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page1.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page1.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page1);


                                        final Page page2 = new Page();
                                        page2.setName("S1_Page2");
                                        page2.setLastModifiedUser(toby);
                                        page2.setCreator(toby);
                                        page2.setSpace(space1);
                                        page2.setParent(null);
                                        page2.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page2.setChildren(new HashSet<Page>() {
                                            {
                                                Page child2_1 = new Page();
                                                child2_1.setName("S1_Page2_1");
                                                child2_1.setLastModifiedUser(jim);
                                                child2_1.setCreator(jim);
                                                child2_1.setSpace(space1);
                                                child2_1.setParent(page2);
                                                child2_1.setAttachments(null);
                                                child2_1.setChildren(null);
                                                child2_1.setPreviousVersions(null);
                                                child2_1.setPriviledges(null);
                                                child2_1.setProperties(null);
                                                add(child2_1);
                                            }
                                        });
                                        page2.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page2.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page2.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page2);
                                    }
                                });
                                add(space1);


                                final Space space2 = new Space();
                                space2.setName("Space2");
                                space2.setCreator(jim);
                                space2.setPriviledges(new HashSet<SpacePriviledge>() {
                                    {
                                        SpacePriviledge priv1 = new SpacePriviledge();
                                        priv1.setName("Priv1");
                                        priv1.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv1);

                                        SpacePriviledge priv2 = new SpacePriviledge();
                                        priv2.setName("Priv2");
                                        priv2.setValues(new HashSet<SpacePriviledgeValue>() {
                                            {
                                                SpacePriviledgeValue val1 = new SpacePriviledgeValue();
                                                val1.setValue("Val1");
                                                add(val1);

                                                SpacePriviledgeValue val2 = new SpacePriviledgeValue();
                                                val2.setValue("Val2");
                                                add(val2);
                                            }
                                        });
                                        add(priv2);
                                    }
                                });
                                space2.setProperties(new HashSet<SpaceProperty>() {
                                    {
                                        SpaceProperty prop1 = new SpaceProperty();
                                        prop1.setName("Prop1");
                                        prop1.setValue("Prop1Value");
                                        add(prop1);

                                        SpaceProperty prop2 = new SpaceProperty();
                                        prop2.setName("Prop2");
                                        prop2.setValue("Prop2Value");
                                        add(prop2);
                                    }
                                });
                                space2.setPages(new HashSet<Page>() {
                                    {
                                        final Page page1 = new Page();
                                        page1.setName("S2_Page1");
                                        page1.setLastModifiedUser(toby);
                                        page1.setCreator(toby);
                                        page1.setSpace(space2);
                                        page1.setParent(null);
                                        page1.setAttachments(new HashSet<PageAttachment>() {
                                            {
                                                PageAttachment att1 = new PageAttachment();
                                                att1.setAttachment("SAMPLE".getBytes());
                                                att1.setContentType("text/plain");
                                                att1.setName("Attachment1");
                                                add(att1);

                                                PageAttachment att2 = new PageAttachment();
                                                att2.setAttachment("SAMPLE".getBytes());
                                                att2.setContentType("text/plain");
                                                att2.setName("Attachment2");
                                                add(att2);
                                            }
                                        });
                                        page1.setChildren(new HashSet<Page>() {
                                            {
                                                Page child1 = new Page();
                                                child1.setName("S2_Page1_1");
                                                child1.setLastModifiedUser(jim);
                                                child1.setCreator(jim);
                                                child1.setSpace(space2);
                                                child1.setParent(page1);
                                                child1.setAttachments(null);
                                                child1.setChildren(null);
                                                child1.setPreviousVersions(null);
                                                child1.setPriviledges(null);
                                                child1.setProperties(null);
                                                add(child1);
                                            }
                                        });
                                        page1.setPreviousVersions(new HashSet<PagePreviousVersion>() {
                                            {
                                                PagePreviousVersion version1 = new PagePreviousVersion();
                                                version1.setModifiedBy(mary);
                                                version1.setContent("PREVIOUS1");
                                                add(version1);

                                                PagePreviousVersion version2 = new PagePreviousVersion();
                                                version2.setModifiedBy(toby);
                                                version2.setContent("PREVIOUS2");
                                                add(version2);
                                            }
                                        });
                                        page1.setPriviledges(new HashSet<PagePriviledge>() {
                                            {
                                                PagePriviledge priv1 = new PagePriviledge();
                                                priv1.setName("PagePriviledge1");
                                                priv1.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge1Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge1Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv1);

                                                PagePriviledge priv2 = new PagePriviledge();
                                                priv2.setName("PagePriviledge2");
                                                priv2.setValues(new HashSet<PagePriviledgeValue>() {
                                                    {
                                                        PagePriviledgeValue privVal1 = new PagePriviledgeValue();
                                                        privVal1.setValue("PagePriviledge2Value1");
                                                        add(privVal1);

                                                        PagePriviledgeValue privVal2 = new PagePriviledgeValue();
                                                        privVal2.setValue("PagePriviledge2Value2");
                                                        add(privVal2);
                                                    }
                                                });
                                                add(priv2);
                                            }
                                        });
                                        page1.setProperties(new HashSet<PageProperty>() {
                                            {
                                                PageProperty prop1 = new PageProperty();
                                                prop1.setName("Page1Prop1");
                                                prop1.setValue("Page1Prop1Value");
                                                add(prop1);

                                                PageProperty prop2 = new PageProperty();
                                                prop2.setName("Page1Prop2");
                                                prop2.setValue("Page1Prop2Value");
                                                add(prop2);
                                            }
                                        });
                                        add(page1);
                                    }
                                });
                                add(space2);
                            }
                        });


                        entityManager.persist(toby);
                        entityManager.persist(jim);
                        entityManager.persist(mary);

                        entityManager.persist(wiki1);
                        entityManager.persist(wiki2);

                        return null;
                    }
                });
            }
        });
    }

    public void testGetAllWikis() throws Exception {
        TemplateManagementService service = getTemplateManagementService();
        UiWikis _wikis = service.getAllWikis(new PagingInfo());

        List<UiWiki> wikis = new ArrayList<UiWiki>(_wikis.getWikis());
        Collections.sort(wikis, new UiWikiNameComparator());

        assertEquals(wikis.size(), 2);
        assertEquals(wikis.get(0).getName(), "Wiki1");
        assertEquals(wikis.get(1).getName(), "Wiki2");

        UiWiki wiki1 = wikis.get(0);

        UiWiki wiki2 = wikis.get(1);
    }

    public void testFindWikiByNameAndById() throws Exception {
        TemplateManagementService service = getTemplateManagementService();
        UiWiki wiki1 = service.findWikiByName("Wiki1");

        assertNotNull(wiki1);
        assertEquals(wiki1.getName(), "Wiki1");

        UiWiki wiki2 = service.findWikiById(wiki1.getId());
        assertNotNull(wiki2);
        assertEquals(wiki2.getName(), "Wiki1");
    }


    


    protected TemplateManagementService getTemplateManagementService() {
        return (TemplateManagementService) getApplicationContext().getBean("templateManagementService");
    }

}
