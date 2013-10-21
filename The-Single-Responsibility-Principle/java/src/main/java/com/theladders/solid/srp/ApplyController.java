package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{

  private ApplicationProcess theApplicationProcess;
  private JobseekerProfileManager theJobseekerProfileManager;
  private JobSearchService theJobSearchService;

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    theJobseekerProfileManager = jobseekerProfileManager;
    theJobSearchService = jobSearchService;
    theApplicationProcess = new ApplicationProcess( jobApplicationSystem,
          resumeManager,
          myResumeManager);
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {

    int jobId = RequestInterpreter.getJobId(request);

    Job job = theJobSearchService.getJob(jobId);

    if (job == null)
    {
      provideInvalidJobView(response, jobId);
      return response;
    }


    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();

    Jobseeker jobseeker = RequestInterpreter.getJobseeker(request);

    try
    {
      theApplicationProcess.apply(jobseeker, job, origFileName, RequestInterpreter.useExistingResume(request), RequestInterpreter.makeResumeActive(request));
    }
    catch (Exception e)
    {
      errList.add("We could not process your application.");
      provideErrorView(response, errList, model);
      return response;
    }


    model.put("jobId", job.getJobId());

    ProfileService aProfileService = new ProfileService(jobseeker, theJobseekerProfileManager);

    if (aProfileService.needsResumeCompletion(jobseeker.isPremium()))
    {
      provideResumeCompletionView(response, model);
      return response;
    }

    provideApplySuccessView(response, model);

    return response;
  }

  private static void provideApplySuccessView(HttpResponse response, Map<String, Object> model)
  {
    Result result = new Result("success", model);
    response.setResult(result);
  }

  private static void provideResumeCompletionView(HttpResponse response, Map<String, Object> model)
  {
    Result result = new Result("completeResumePlease", model);
    response.setResult(result);
  }

  private static void provideErrorView(HttpResponse response, List<String> errList, Map<String, Object> model)
  {
   Result result = new Result("error", model, errList);
   response.setResult(result);
  }

  private static void provideInvalidJobView(HttpResponse response, int jobId)
  {
    Map<String, Object> model = new HashMap<>();
    model.put("jobId", jobId);

    Result result = new Result("invalidJob", model);
    response.setResult(result);
  }
}
