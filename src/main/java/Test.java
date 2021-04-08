import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author liutao
 * @date Created in 2020/10/14 16:55
 * @description
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        String string = "java.io.FileNotFoundException: SRVE0190E: 找不到文件：/resources/NCCExtend/extend_uap/msgcenter/10160501/10160501APPROVE/index.js at com.ibm.ws.webcontainer.extension.DefaultExtensionProcessor._processEDR(DefaultExtensionProcessor.java:977) at com.ibm.ws.webcontainer.extension.DefaultExtensionProcessor.processEDR(DefaultExtensionProcessor.java:958) at com.ibm.ws.webcontainer.extension.DefaultExtensionProcessor.handleRequest(DefaultExtensionProcessor.java:486) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.invokeTarget(WebAppFilterChain.java:143) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:96) at nccloud.framework.core.filter.CorsFilter.handleNonCORS(CorsFilter.java:470) at nccloud.framework.core.filter.CorsFilter.doFilter(CorsFilter.java:155) at com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90) at nccloud.framework.core.filter.SecurityFilter.doFilter(SecurityFilter.java:87) at com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90) at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) at com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90) at nccloud.framework.core.filter.EntryLeaveFilter.doFilter(EntryLeaveFilter.java:50) at com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90) at nccloud.framework.core.filter.HostFilter.doFilter(HostFilter.java:47) at com.ibm.ws.webcontainer.filter.FilterInstanceWrapper.doFilter(FilterInstanceWrapper.java:197) at com.ibm.ws.webcontainer.filter.WebAppFilterChain.doFilter(WebAppFilterChain.java:90) at com.ibm.ws.webcontainer.filter.WebAppFilterManager.doFilter(WebAppFilterManager.java:969) at com.ibm.ws.webcontainer.filter.WebAppFilterManager.invokeFilters(WebAppFilterManager.java:1109) at com.ibm.ws.webcontainer.webapp.WebApp.handleRequest(WebApp.java:4219) at com.ibm.ws.webcontainer.webapp.WebAppImpl.handleRequest(WebAppImpl.java:2208) at com.ibm.ws.webcontainer.webapp.WebGroup.handleRequest(WebGroup.java:304) at com.ibm.ws.webcontainer.WebContainer.handleRequest(WebContainer.java:1030) at com.ibm.ws.webcontainer.WSWebContainer.handleRequest(WSWebContainer.java:1817) at com.ibm.ws.webcontainer.channel.WCChannelLink.ready(WCChannelLink.java:382) at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleDiscrimination(HttpInboundLink.java:465) at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.handleNewRequest(HttpInboundLink.java:532) at com.ibm.ws.http.channel.inbound.impl.HttpInboundLink.processRequest(HttpInboundLink.java:318) at com.ibm.ws.http.channel.inbound.impl.HttpICLReadCallback.complete(HttpICLReadCallback.java:88) at com.ibm.ws.tcp.channel.impl.AioReadCompletionListener.futureCompleted(AioReadCompletionListener.java:175) at com.ibm.io.async.AbstractAsyncFuture.invokeCallback(AbstractAsyncFuture.java:217) at com.ibm.io.async.AsyncChannelFuture.fireCompletionActions(AsyncChannelFuture.java:161) at com.ibm.io.async.AsyncFuture.completed(AsyncFuture.java:138) at com.ibm.io.async.ResultHandler.complete(ResultHandler.java:204) at com.ibm.io.async.ResultHandler.runEventProcessingLoop(ResultHandler.java:775) at com.ibm.io.async.ResultHandler$2.run(ResultHandler.java:905) at com.ibm.ws.util.ThreadPool$Worker.run(ThreadPool.java:1909)";

        String collect = Arrays.stream(string.split("at "))
                .filter(str -> !str.startsWith("org.") && !str.startsWith("java"))
                .filter(str -> !str.startsWith("nc.itf.framework."))
                .filter(str -> !str.startsWith("nc.bs.framework.") && !str.startsWith("nccloud.framework."))
                .collect(Collectors.joining("\n at "));
        System.out.println(collect);


        String sql = "SELECT " +
                "o.pk_org AS pk_org, " +
                "FROM " +
                "org_orgs o " +
                "INNER JOIN org_adminorg a ON o.pk_org = a.pk_adminorg " +
                "WHERE " +
                "a.enablestate = 2 " +
                "AND a.islastversion = 'Y' " +
                "AND a.dr = 0 " +
                "AND ";

        System.out.println(sql);

    }

}
