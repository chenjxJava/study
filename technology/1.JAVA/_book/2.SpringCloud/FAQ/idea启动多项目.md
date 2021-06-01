### 1.idea项目中运行多个springboot项目

![1599534835074](..\..\pic\1599534835074.png)



.idea->workspace.xml添加如下配置：

```xml
<component name="RunDashboard">
  <option name="configurationTypes">
    <set>
      <option value="SpringBootApplicationConfigurationType" />
    </set>
  </option>
  <option name="ruleStates">
    <list>
      <RuleState>
        <option name="name" value="ConfigurationTypeDashboardGroupingRule" />
      </RuleState>
      <RuleState>
        <option name="name" value="StatusDashboardGroupingRule" />
      </RuleState>
    </list>
  </option>
</component>


```

![1599534997213](..\..\pic\1599534997213.png)





